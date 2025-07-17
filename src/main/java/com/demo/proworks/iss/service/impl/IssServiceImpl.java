package com.demo.proworks.iss.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.demo.proworks.att.dao.AttDAO;
import com.demo.proworks.att.vo.AttListVo;
import com.demo.proworks.att.vo.AttVo;
import com.demo.proworks.common.s3.S3Uploader;
import com.demo.proworks.iss.dao.IssDAO;
import com.demo.proworks.iss.service.IssService;
import com.demo.proworks.iss.vo.IssVo;

@Service("issServiceImpl")
public class IssServiceImpl implements IssService {

	@Resource(name = "issDAO")
	private IssDAO issDAO;

	@Resource(name = "attDAO")
	private AttDAO attDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Resource
	private S3Uploader s3Uploader;

	@Resource
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Value("${file.upload.path}")
	private String uploadPath;

	public IssVo selectIss(IssVo vo) throws Exception {
		return issDAO.selectIss(vo);
	}

	public List<IssVo> selectListIss(IssVo vo) throws Exception {
		return issDAO.selectListIss(vo);
	}

	public long selectListCountIss(IssVo vo) throws Exception {
		return issDAO.selectListCountIss(vo);
	}

	public void insertIss(IssVo vo) throws Exception {
		issDAO.insertIss(vo);
	}

	public void updateIss(IssVo vo) throws Exception {
		issDAO.updateIss(vo);
	}

	public void deleteIss(IssVo vo) throws Exception {
		issDAO.deleteIss(vo);
	}

	@Transactional(rollbackFor = Exception.class)
	public IssVo insertIssWithFiles(IssVo issVo, MultipartFile[] files) throws Exception {
		List<String> uploadedS3Keys = new ArrayList<>();

		try {
			// 1. 이슈 등록
			issDAO.insertIss(issVo);
			String issueId = issVo.getId();
			if (issueId == null)
				throw new RuntimeException("이슈 ID 생성 실패");

			// 2. 파일 업로드
			if (files != null && files.length > 0) {
				for (MultipartFile file : files) {
					if (!file.isEmpty()) {
						AttVo attVo = uploadAndSaveFile(file, "ISSUE", issueId);
						uploadedS3Keys.add(attVo.getS3Key());
					}
				}
			}

			return issVo;

		} catch (Exception e) {
			for (String s3Key : uploadedS3Keys) {
				try {
					amazonS3.deleteObject(bucketName, s3Key);
				} catch (Exception ignored) {
				}
			}
			throw new RuntimeException("이슈 등록 실패", e);
		}
	}

	private AttVo uploadAndSaveFile(MultipartFile file, String refType, String refId) throws Exception {
		String s3Key = s3Uploader.upload(file, uploadPath + "/" + refType);

		String originalName = file.getOriginalFilename();
		String extension = getFileExtension(originalName);
		String storedName = s3Key.substring(s3Key.lastIndexOf("/") + 1);

		// file 테이블 insert (AUTO_INCREMENT로 ID 자동 생성)
		AttVo fileVo = new AttVo();
		fileVo.setOriginalFileName(originalName);
		fileVo.setStoredFileName(storedName);
		fileVo.setFileSize(String.valueOf(file.getSize()));
		fileVo.setFileExtension(extension);
		fileVo.setS3Key(s3Key);
		attDAO.insertFile(fileVo);

		// insertFile 후 자동 생성된 fileId를 가져옴 (숫자)
		String fileId = fileVo.getFileId(); // 이게 "4" 같은 숫자 문자열

		// attachment 테이블 insert
		AttVo attachmentVo = new AttVo();
		attachmentVo.setId(UUID.randomUUID().toString()); // attachment 테이블의 PK
		attachmentVo.setFileId(fileId); // 위에서 생성된 숫자 ID 사용
		attachmentVo.setRefType(refType);
		attachmentVo.setRefId(refId);
		attachmentVo.setIsDeleted("N");
		attDAO.insertFileAttachment(attachmentVo);

		return fileVo;
	}

	private String getFileExtension(String fileName) {
		if (fileName == null || fileName.isEmpty())
			return "";
		int dot = fileName.lastIndexOf(".");
		return (dot == -1) ? "" : fileName.substring(dot + 1).toLowerCase();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IssVo updateIssWithFiles(IssVo issVo, MultipartFile[] files) throws Exception {
		List<String> uploadedS3Keys = new ArrayList<>();

		try {
			System.out.println("=== 이슈 수정 with 파일 시작 ===");
			System.out.println("수정할 이슈 ID: " + issVo.getId());

			// 1. 이슈 ID 유효성 검사
			if (issVo.getId() == null || issVo.getId().trim().isEmpty()) {
				throw new RuntimeException("수정할 이슈 ID가 필요합니다.");
			}

			// 2. 기존 이슈 존재 여부 확인
			IssVo existingIssue = issDAO.selectIss(issVo);
			if (existingIssue == null) {
				throw new RuntimeException("수정할 이슈를 찾을 수 없습니다. ID: " + issVo.getId());
			}

			// 3. 이슈 정보 수정
			int updateResult = issDAO.updateIss(issVo);
			if (updateResult <= 0) {
				throw new RuntimeException("이슈 정보 수정 실패");
			}
			System.out.println("이슈 정보 수정 완료");

			// 4. 새로운 파일 업로드 (기존 파일은 유지)
			if (files != null && files.length > 0) {
				for (MultipartFile file : files) {
					if (!file.isEmpty()) {
						AttVo attVo = uploadAndSaveFile(file, "ISSUE", issVo.getId());
						uploadedS3Keys.add(attVo.getS3Key());
						System.out.println("새 파일 업로드: " + file.getOriginalFilename());
					}
				}
			}

			System.out.println("=== 이슈 수정 with 파일 완료 ===");
			return issVo;

		} catch (Exception e) {
			System.err.println("이슈 수정 실패: " + e.getMessage());

			// 새로 업로드된 파일들 S3에서 삭제 (롤백)
			for (String s3Key : uploadedS3Keys) {
				try {
					amazonS3.deleteObject(bucketName, s3Key);
					System.out.println("S3 파일 롤백: " + s3Key);
				} catch (Exception ignored) {
				}
			}

			throw new RuntimeException("이슈 수정 실패: " + e.getMessage(), e);
		}
	}

	/**
	 * [프로웍스용] 기존 로컬 업로드된 파일 정보를 기반으로 S3에 파일을 업로드하고, 이슈 및 첨부 파일 정보를 DB에 저장함.
	 *
	 * @param issVo            이슈 정보
	 * @param fileList         로컬에 임시 저장된 파일 목록
	 * @param originalNameList 원본 파일명 목록
	 */
	@Override
	@Transactional
	public void insertIssWithStoredFiles(IssVo issVo, List<File> fileList, List<String> originalNameList) {
		// 1. 이슈 등록 (ID 자동 생성)
		issDAO.insertIss(issVo);
		String issId = issVo.getId();

		if (issId == null || issId.isEmpty()) {
			throw new IllegalStateException("이슈 ID가 생성되지 않았습니다.");
		}

		// 2. 파일 업로드 및 등록
		for (int i = 0; i < fileList.size(); i++) {
			File file = fileList.get(i);
			String originalName = originalNameList.get(i);

			// 1) S3 업로드
			String s3Key = s3Uploader.upload(file, "issues");
			String bucket = bucketName;

			System.out.println("s3Key" + s3Key);
			System.out.println("bucket" + bucket);

			// 2) file 테이블 등록
			AttVo attVo = new AttVo();
			attVo.setOriginalFileName(originalName);
			attVo.setStoredFileName(file.getName());
			attVo.setFileExtension(FilenameUtils.getExtension(originalName));
			attVo.setFileSize(String.valueOf(file.length()));
			attVo.setS3Bucket(bucketName);
			attVo.setS3Key(s3Key);
			attVo.setAttachedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			System.out.println("attVo" + attVo);

			// insertFile() 호출 시 fileId 생성됨
			attDAO.insertFile(attVo);

			if (attVo.getFileId() == null) {
				throw new IllegalStateException("파일 ID가 생성되지 않았습니다.");
			}

			// 3) file_attachments 테이블 등록
			attVo.setRefType("ISSUE");
			attVo.setRefId(issId);
			attDAO.insertFileAttachment(attVo); // 이 메서드는 내부적으로 insertFileAttachment 호출
		}
	}

}
