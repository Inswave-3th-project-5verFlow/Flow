package com.demo.proworks.test.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "테스트관리")
public class TestVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public TestVo(){
    }

    @ElDtoField(logicalName = "테스트 고유 ID", physicalName = "id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String id;

    @ElDtoField(logicalName = "연결된 업무 ID", physicalName = "taskId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskId;

    @ElDtoField(logicalName = "테스트 케이스명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "테스트 카테고리", physicalName = "category", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String category;

    @ElDtoField(logicalName = "테스트 실행 시나리오", physicalName = "scenario", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scenario;

    @ElDtoField(logicalName = "테스트 입력 데이터", physicalName = "inputData", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String inputData;

    @ElDtoField(logicalName = "예상 결과", physicalName = "expectedResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String expectedResult;

    @ElDtoField(logicalName = "실제 결과", physicalName = "actualResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String actualResult;

    @ElDtoField(logicalName = "테스트 결과", physicalName = "testResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testResult;

    @ElDtoField(logicalName = "테스트 담당자", physicalName = "assignee", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String assignee;

    @ElDtoField(logicalName = "테스트 실행일", physicalName = "executionDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String executionDate;

    @ElDtoField(logicalName = "테스트 소요시간(분)", physicalName = "duration", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String duration;

    @ElDtoField(logicalName = "삭제 여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElDtoField(logicalName = "등록일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "연결된 업무명", physicalName = "taskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskName;

    @ElDtoField(logicalName = "담당자명", physicalName = "userName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userName;

    @ElVoField(physicalName = "id")
    public String getId(){
        String ret = this.id;
        return ret;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "taskId")
    public String getTaskId(){
        String ret = this.taskId;
        return ret;
    }

    @ElVoField(physicalName = "taskId")
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "category")
    public String getCategory(){
        String ret = this.category;
        return ret;
    }

    @ElVoField(physicalName = "category")
    public void setCategory(String category){
        this.category = category;
    }

    @ElVoField(physicalName = "scenario")
    public String getScenario(){
        String ret = this.scenario;
        return ret;
    }

    @ElVoField(physicalName = "scenario")
    public void setScenario(String scenario){
        this.scenario = scenario;
    }

    @ElVoField(physicalName = "inputData")
    public String getInputData(){
        String ret = this.inputData;
        return ret;
    }

    @ElVoField(physicalName = "inputData")
    public void setInputData(String inputData){
        this.inputData = inputData;
    }

    @ElVoField(physicalName = "expectedResult")
    public String getExpectedResult(){
        String ret = this.expectedResult;
        return ret;
    }

    @ElVoField(physicalName = "expectedResult")
    public void setExpectedResult(String expectedResult){
        this.expectedResult = expectedResult;
    }

    @ElVoField(physicalName = "actualResult")
    public String getActualResult(){
        String ret = this.actualResult;
        return ret;
    }

    @ElVoField(physicalName = "actualResult")
    public void setActualResult(String actualResult){
        this.actualResult = actualResult;
    }

    @ElVoField(physicalName = "testResult")
    public String getTestResult(){
        String ret = this.testResult;
        return ret;
    }

    @ElVoField(physicalName = "testResult")
    public void setTestResult(String testResult){
        this.testResult = testResult;
    }

    @ElVoField(physicalName = "assignee")
    public String getAssignee(){
        String ret = this.assignee;
        return ret;
    }

    @ElVoField(physicalName = "assignee")
    public void setAssignee(String assignee){
        this.assignee = assignee;
    }

    @ElVoField(physicalName = "executionDate")
    public String getExecutionDate(){
        String ret = this.executionDate;
        return ret;
    }

    @ElVoField(physicalName = "executionDate")
    public void setExecutionDate(String executionDate){
        this.executionDate = executionDate;
    }

    @ElVoField(physicalName = "duration")
    public String getDuration(){
        String ret = this.duration;
        return ret;
    }

    @ElVoField(physicalName = "duration")
    public void setDuration(String duration){
        this.duration = duration;
    }

    @ElVoField(physicalName = "isDeleted")
    public String getIsDeleted(){
        String ret = this.isDeleted;
        return ret;
    }

    @ElVoField(physicalName = "isDeleted")
    public void setIsDeleted(String isDeleted){
        this.isDeleted = isDeleted;
    }

    @ElVoField(physicalName = "createdAt")
    public String getCreatedAt(){
        String ret = this.createdAt;
        return ret;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public String getUpdatedAt(){
        String ret = this.updatedAt;
        return ret;
    }

    @ElVoField(physicalName = "updatedAt")
    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    @ElVoField(physicalName = "taskName")
    public String getTaskName(){
        String ret = this.taskName;
        return ret;
    }

    @ElVoField(physicalName = "taskName")
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    @ElVoField(physicalName = "userName")
    public String getUserName(){
        String ret = this.userName;
        return ret;
    }

    @ElVoField(physicalName = "userName")
    public void setUserName(String userName){
        this.userName = userName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TestVo [");
        sb.append("id").append("=").append(id).append(",");
        sb.append("taskId").append("=").append(taskId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("category").append("=").append(category).append(",");
        sb.append("scenario").append("=").append(scenario).append(",");
        sb.append("inputData").append("=").append(inputData).append(",");
        sb.append("expectedResult").append("=").append(expectedResult).append(",");
        sb.append("actualResult").append("=").append(actualResult).append(",");
        sb.append("testResult").append("=").append(testResult).append(",");
        sb.append("assignee").append("=").append(assignee).append(",");
        sb.append("executionDate").append("=").append(executionDate).append(",");
        sb.append("duration").append("=").append(duration).append(",");
        sb.append("isDeleted").append("=").append(isDeleted).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("taskName").append("=").append(taskName).append(",");
        sb.append("userName").append("=").append(userName);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
    }


    @Override
    public void _xStreamDec() {
    }


}
