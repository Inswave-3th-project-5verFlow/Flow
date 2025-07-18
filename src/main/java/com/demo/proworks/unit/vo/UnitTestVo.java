package com.demo.proworks.unit.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "단위테스트 관리")
public class UnitTestVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UnitTestVo(){
    }

    @ElDtoField(logicalName = "테스트케이스ID", physicalName = "testCaseId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testCaseId;

    @ElDtoField(logicalName = "업무ID", physicalName = "taskId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskId;

    @ElDtoField(logicalName = "업무명", physicalName = "taskName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String taskName;

    @ElDtoField(logicalName = "테스트케이스명", physicalName = "testCaseName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testCaseName;

    @ElDtoField(logicalName = "테스트대상", physicalName = "testTarget", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testTarget;

    @ElDtoField(logicalName = "테스트타입", physicalName = "testType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testType;

    @ElDtoField(logicalName = "우선순위", physicalName = "priority", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String priority;

    @ElDtoField(logicalName = "테스트설명", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "전제조건", physicalName = "precondition", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String precondition;

    @ElDtoField(logicalName = "테스트데이터", physicalName = "testData", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testData;

    @ElDtoField(logicalName = "실행단계", physicalName = "testSteps", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testSteps;

    @ElDtoField(logicalName = "예상결과", physicalName = "expectedResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String expectedResult;

    @ElDtoField(logicalName = "실제결과", physicalName = "actualResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String actualResult;

    @ElDtoField(logicalName = "테스트상태", physicalName = "testStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testStatus;

    @ElDtoField(logicalName = "실행일시", physicalName = "executionDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String executionDate;

    @ElDtoField(logicalName = "소요시간", physicalName = "duration", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String duration;

    @ElDtoField(logicalName = "비고", physicalName = "notes", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String notes;

    @ElDtoField(logicalName = "연관요구사항", physicalName = "requirements", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String requirements;

    @ElDtoField(logicalName = "담당자", physicalName = "assignee", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String assignee;

    @ElDtoField(logicalName = "삭제여부", physicalName = "isDeleted", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isDeleted;

    @ElDtoField(logicalName = "생성일시", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "생성자", physicalName = "createdBy", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdBy;

    @ElDtoField(logicalName = "수정자", physicalName = "updatedBy", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedBy;

    @ElDtoField(logicalName = "프로젝트ID", physicalName = "pjtId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String pjtId;

    @ElDtoField(logicalName = "검색_업무ID", physicalName = "searchTaskId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchTaskId;

    @ElDtoField(logicalName = "검색_테스트상태", physicalName = "searchTestStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchTestStatus;

    @ElDtoField(logicalName = "검색_담당자", physicalName = "searchAssignee", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchAssignee;

    @ElDtoField(logicalName = "검색_업무이름", physicalName = "searchTestCaseName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchTestCaseName;

    @ElDtoField(logicalName = "", physicalName = "searchTestTarget", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchTestTarget;

    @ElDtoField(logicalName = "", physicalName = "searchTestType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchTestType;

    @ElDtoField(logicalName = "", physicalName = "searchPriority", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String searchPriority;

    @ElDtoField(logicalName = "", physicalName = "dateFrom", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String dateFrom;

    @ElDtoField(logicalName = "", physicalName = "dateTo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String dateTo;

    @ElVoField(physicalName = "testCaseId")
    public String getTestCaseId(){
        String ret = this.testCaseId;
        return ret;
    }

    @ElVoField(physicalName = "testCaseId")
    public void setTestCaseId(String testCaseId){
        this.testCaseId = testCaseId;
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

    @ElVoField(physicalName = "taskName")
    public String getTaskName(){
        String ret = this.taskName;
        return ret;
    }

    @ElVoField(physicalName = "taskName")
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    @ElVoField(physicalName = "testCaseName")
    public String getTestCaseName(){
        String ret = this.testCaseName;
        return ret;
    }

    @ElVoField(physicalName = "testCaseName")
    public void setTestCaseName(String testCaseName){
        this.testCaseName = testCaseName;
    }

    @ElVoField(physicalName = "testTarget")
    public String getTestTarget(){
        String ret = this.testTarget;
        return ret;
    }

    @ElVoField(physicalName = "testTarget")
    public void setTestTarget(String testTarget){
        this.testTarget = testTarget;
    }

    @ElVoField(physicalName = "testType")
    public String getTestType(){
        String ret = this.testType;
        return ret;
    }

    @ElVoField(physicalName = "testType")
    public void setTestType(String testType){
        this.testType = testType;
    }

    @ElVoField(physicalName = "priority")
    public String getPriority(){
        String ret = this.priority;
        return ret;
    }

    @ElVoField(physicalName = "priority")
    public void setPriority(String priority){
        this.priority = priority;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        String ret = this.description;
        return ret;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "precondition")
    public String getPrecondition(){
        String ret = this.precondition;
        return ret;
    }

    @ElVoField(physicalName = "precondition")
    public void setPrecondition(String precondition){
        this.precondition = precondition;
    }

    @ElVoField(physicalName = "testData")
    public String getTestData(){
        String ret = this.testData;
        return ret;
    }

    @ElVoField(physicalName = "testData")
    public void setTestData(String testData){
        this.testData = testData;
    }

    @ElVoField(physicalName = "testSteps")
    public String getTestSteps(){
        String ret = this.testSteps;
        return ret;
    }

    @ElVoField(physicalName = "testSteps")
    public void setTestSteps(String testSteps){
        this.testSteps = testSteps;
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

    @ElVoField(physicalName = "testStatus")
    public String getTestStatus(){
        String ret = this.testStatus;
        return ret;
    }

    @ElVoField(physicalName = "testStatus")
    public void setTestStatus(String testStatus){
        this.testStatus = testStatus;
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

    @ElVoField(physicalName = "notes")
    public String getNotes(){
        String ret = this.notes;
        return ret;
    }

    @ElVoField(physicalName = "notes")
    public void setNotes(String notes){
        this.notes = notes;
    }

    @ElVoField(physicalName = "requirements")
    public String getRequirements(){
        String ret = this.requirements;
        return ret;
    }

    @ElVoField(physicalName = "requirements")
    public void setRequirements(String requirements){
        this.requirements = requirements;
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

    @ElVoField(physicalName = "createdBy")
    public String getCreatedBy(){
        String ret = this.createdBy;
        return ret;
    }

    @ElVoField(physicalName = "createdBy")
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    @ElVoField(physicalName = "updatedBy")
    public String getUpdatedBy(){
        String ret = this.updatedBy;
        return ret;
    }

    @ElVoField(physicalName = "updatedBy")
    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }

    @ElVoField(physicalName = "pjtId")
    public String getPjtId(){
        String ret = this.pjtId;
        return ret;
    }

    @ElVoField(physicalName = "pjtId")
    public void setPjtId(String pjtId){
        this.pjtId = pjtId;
    }

    @ElVoField(physicalName = "searchTaskId")
    public String getSearchTaskId(){
        String ret = this.searchTaskId;
        return ret;
    }

    @ElVoField(physicalName = "searchTaskId")
    public void setSearchTaskId(String searchTaskId){
        this.searchTaskId = searchTaskId;
    }

    @ElVoField(physicalName = "searchTestStatus")
    public String getSearchTestStatus(){
        String ret = this.searchTestStatus;
        return ret;
    }

    @ElVoField(physicalName = "searchTestStatus")
    public void setSearchTestStatus(String searchTestStatus){
        this.searchTestStatus = searchTestStatus;
    }

    @ElVoField(physicalName = "searchAssignee")
    public String getSearchAssignee(){
        String ret = this.searchAssignee;
        return ret;
    }

    @ElVoField(physicalName = "searchAssignee")
    public void setSearchAssignee(String searchAssignee){
        this.searchAssignee = searchAssignee;
    }

    @ElVoField(physicalName = "searchTestCaseName")
    public String getSearchTestCaseName(){
        String ret = this.searchTestCaseName;
        return ret;
    }

    @ElVoField(physicalName = "searchTestCaseName")
    public void setSearchTestCaseName(String searchTestCaseName){
        this.searchTestCaseName = searchTestCaseName;
    }

    @ElVoField(physicalName = "searchTestTarget")
    public String getSearchTestTarget(){
        String ret = this.searchTestTarget;
        return ret;
    }

    @ElVoField(physicalName = "searchTestTarget")
    public void setSearchTestTarget(String searchTestTarget){
        this.searchTestTarget = searchTestTarget;
    }

    @ElVoField(physicalName = "searchTestType")
    public String getSearchTestType(){
        String ret = this.searchTestType;
        return ret;
    }

    @ElVoField(physicalName = "searchTestType")
    public void setSearchTestType(String searchTestType){
        this.searchTestType = searchTestType;
    }

    @ElVoField(physicalName = "searchPriority")
    public String getSearchPriority(){
        String ret = this.searchPriority;
        return ret;
    }

    @ElVoField(physicalName = "searchPriority")
    public void setSearchPriority(String searchPriority){
        this.searchPriority = searchPriority;
    }

    @ElVoField(physicalName = "dateFrom")
    public String getDateFrom(){
        String ret = this.dateFrom;
        return ret;
    }

    @ElVoField(physicalName = "dateFrom")
    public void setDateFrom(String dateFrom){
        this.dateFrom = dateFrom;
    }

    @ElVoField(physicalName = "dateTo")
    public String getDateTo(){
        String ret = this.dateTo;
        return ret;
    }

    @ElVoField(physicalName = "dateTo")
    public void setDateTo(String dateTo){
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnitTestVo [");
        sb.append("testCaseId").append("=").append(testCaseId).append(",");
        sb.append("taskId").append("=").append(taskId).append(",");
        sb.append("taskName").append("=").append(taskName).append(",");
        sb.append("testCaseName").append("=").append(testCaseName).append(",");
        sb.append("testTarget").append("=").append(testTarget).append(",");
        sb.append("testType").append("=").append(testType).append(",");
        sb.append("priority").append("=").append(priority).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("precondition").append("=").append(precondition).append(",");
        sb.append("testData").append("=").append(testData).append(",");
        sb.append("testSteps").append("=").append(testSteps).append(",");
        sb.append("expectedResult").append("=").append(expectedResult).append(",");
        sb.append("actualResult").append("=").append(actualResult).append(",");
        sb.append("testStatus").append("=").append(testStatus).append(",");
        sb.append("executionDate").append("=").append(executionDate).append(",");
        sb.append("duration").append("=").append(duration).append(",");
        sb.append("notes").append("=").append(notes).append(",");
        sb.append("requirements").append("=").append(requirements).append(",");
        sb.append("assignee").append("=").append(assignee).append(",");
        sb.append("isDeleted").append("=").append(isDeleted).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("createdBy").append("=").append(createdBy).append(",");
        sb.append("updatedBy").append("=").append(updatedBy).append(",");
        sb.append("pjtId").append("=").append(pjtId).append(",");
        sb.append("searchTaskId").append("=").append(searchTaskId).append(",");
        sb.append("searchTestStatus").append("=").append(searchTestStatus).append(",");
        sb.append("searchAssignee").append("=").append(searchAssignee).append(",");
        sb.append("searchTestCaseName").append("=").append(searchTestCaseName).append(",");
        sb.append("searchTestTarget").append("=").append(searchTestTarget).append(",");
        sb.append("searchTestType").append("=").append(searchTestType).append(",");
        sb.append("searchPriority").append("=").append(searchPriority).append(",");
        sb.append("dateFrom").append("=").append(dateFrom).append(",");
        sb.append("dateTo").append("=").append(dateTo);
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
