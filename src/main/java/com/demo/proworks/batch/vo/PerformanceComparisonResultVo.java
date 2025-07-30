package com.demo.proworks.batch.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "성능 비교 결과")
public class PerformanceComparisonResultVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PerformanceComparisonResultVo(){
    }

    @ElDtoField(logicalName = "성공여부", physicalName = "success", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String success;

    @ElDtoField(logicalName = "데모제목", physicalName = "demoTitle", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String demoTitle;

    @ElDtoField(logicalName = "파일명", physicalName = "fileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileName;

    @ElDtoField(logicalName = "파일크기MB", physicalName = "fileSizeMB", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String fileSizeMB;

    @ElDtoField(logicalName = "테스트시간", physicalName = "testTimestamp", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String testTimestamp;

    @ElDtoField(logicalName = "시스템CPU코어", physicalName = "systemCpuCores", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String systemCpuCores;

    @ElDtoField(logicalName = "시스템최대메모리MB", physicalName = "systemMaxMemoryMB", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String systemMaxMemoryMB;

    @ElDtoField(logicalName = "기존방식설명", physicalName = "beforeMethodName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeMethodName;

    @ElDtoField(logicalName = "기존방식상세설명", physicalName = "beforeDescription", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeDescription;

    @ElDtoField(logicalName = "기존방식소요시간MS", physicalName = "beforeDuration", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeDuration;

    @ElDtoField(logicalName = "기존방식소요시간초", physicalName = "beforeDurationSeconds", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeDurationSeconds;

    @ElDtoField(logicalName = "기존방식처리량", physicalName = "beforeThroughput", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeThroughput;

    @ElDtoField(logicalName = "기존방식메모리사용MB", physicalName = "beforeMemoryUsedMB", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeMemoryUsedMB;

    @ElDtoField(logicalName = "기존방식파일ID", physicalName = "beforeFileId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String beforeFileId;

    @ElDtoField(logicalName = "개선방식설명", physicalName = "afterMethodName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterMethodName;

    @ElDtoField(logicalName = "개선방식상세설명", physicalName = "afterDescription", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterDescription;

    @ElDtoField(logicalName = "개선방식소요시간MS", physicalName = "afterDuration", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterDuration;

    @ElDtoField(logicalName = "개선방식소요시간초", physicalName = "afterDurationSeconds", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterDurationSeconds;

    @ElDtoField(logicalName = "개선방식처리량", physicalName = "afterThroughput", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterThroughput;

    @ElDtoField(logicalName = "개선방식메모리사용MB", physicalName = "afterMemoryUsedMB", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterMemoryUsedMB;

    @ElDtoField(logicalName = "개선방식파일ID", physicalName = "afterFileId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String afterFileId;

    @ElDtoField(logicalName = "속도개선율", physicalName = "speedImprovement", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String speedImprovement;

    @ElDtoField(logicalName = "처리량개선율", physicalName = "throughputImprovement", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String throughputImprovement;

    @ElDtoField(logicalName = "메모리효율성", physicalName = "memoryEfficiency", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String memoryEfficiency;

    @ElDtoField(logicalName = "속도향상배수", physicalName = "speedupFactor", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String speedupFactor;

    @ElDtoField(logicalName = "메모리효율성설명", physicalName = "memoryAdvantage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String memoryAdvantage;

    @ElDtoField(logicalName = "처리속도설명", physicalName = "speedAdvantage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String speedAdvantage;

    @ElDtoField(logicalName = "확장성설명", physicalName = "scalabilityAdvantage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scalabilityAdvantage;

    @ElDtoField(logicalName = "결론메시지", physicalName = "conclusion", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String conclusion;

    @ElDtoField(logicalName = "안정성설명", physicalName = "stabilityAdvantage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String stabilityAdvantage;

    @ElDtoField(logicalName = "에러메시지", physicalName = "errorMessage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String errorMessage;

    @ElVoField(physicalName = "success")
    public String getSuccess(){
        String ret = this.success;
        return ret;
    }

    @ElVoField(physicalName = "success")
    public void setSuccess(String success){
        this.success = success;
    }

    @ElVoField(physicalName = "demoTitle")
    public String getDemoTitle(){
        String ret = this.demoTitle;
        return ret;
    }

    @ElVoField(physicalName = "demoTitle")
    public void setDemoTitle(String demoTitle){
        this.demoTitle = demoTitle;
    }

    @ElVoField(physicalName = "fileName")
    public String getFileName(){
        String ret = this.fileName;
        return ret;
    }

    @ElVoField(physicalName = "fileName")
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    @ElVoField(physicalName = "fileSizeMB")
    public String getFileSizeMB(){
        String ret = this.fileSizeMB;
        return ret;
    }

    @ElVoField(physicalName = "fileSizeMB")
    public void setFileSizeMB(String fileSizeMB){
        this.fileSizeMB = fileSizeMB;
    }

    @ElVoField(physicalName = "testTimestamp")
    public String getTestTimestamp(){
        String ret = this.testTimestamp;
        return ret;
    }

    @ElVoField(physicalName = "testTimestamp")
    public void setTestTimestamp(String testTimestamp){
        this.testTimestamp = testTimestamp;
    }

    @ElVoField(physicalName = "systemCpuCores")
    public String getSystemCpuCores(){
        String ret = this.systemCpuCores;
        return ret;
    }

    @ElVoField(physicalName = "systemCpuCores")
    public void setSystemCpuCores(String systemCpuCores){
        this.systemCpuCores = systemCpuCores;
    }

    @ElVoField(physicalName = "systemMaxMemoryMB")
    public String getSystemMaxMemoryMB(){
        String ret = this.systemMaxMemoryMB;
        return ret;
    }

    @ElVoField(physicalName = "systemMaxMemoryMB")
    public void setSystemMaxMemoryMB(String systemMaxMemoryMB){
        this.systemMaxMemoryMB = systemMaxMemoryMB;
    }

    @ElVoField(physicalName = "beforeMethodName")
    public String getBeforeMethodName(){
        String ret = this.beforeMethodName;
        return ret;
    }

    @ElVoField(physicalName = "beforeMethodName")
    public void setBeforeMethodName(String beforeMethodName){
        this.beforeMethodName = beforeMethodName;
    }

    @ElVoField(physicalName = "beforeDescription")
    public String getBeforeDescription(){
        String ret = this.beforeDescription;
        return ret;
    }

    @ElVoField(physicalName = "beforeDescription")
    public void setBeforeDescription(String beforeDescription){
        this.beforeDescription = beforeDescription;
    }

    @ElVoField(physicalName = "beforeDuration")
    public String getBeforeDuration(){
        String ret = this.beforeDuration;
        return ret;
    }

    @ElVoField(physicalName = "beforeDuration")
    public void setBeforeDuration(String beforeDuration){
        this.beforeDuration = beforeDuration;
    }

    @ElVoField(physicalName = "beforeDurationSeconds")
    public String getBeforeDurationSeconds(){
        String ret = this.beforeDurationSeconds;
        return ret;
    }

    @ElVoField(physicalName = "beforeDurationSeconds")
    public void setBeforeDurationSeconds(String beforeDurationSeconds){
        this.beforeDurationSeconds = beforeDurationSeconds;
    }

    @ElVoField(physicalName = "beforeThroughput")
    public String getBeforeThroughput(){
        String ret = this.beforeThroughput;
        return ret;
    }

    @ElVoField(physicalName = "beforeThroughput")
    public void setBeforeThroughput(String beforeThroughput){
        this.beforeThroughput = beforeThroughput;
    }

    @ElVoField(physicalName = "beforeMemoryUsedMB")
    public String getBeforeMemoryUsedMB(){
        String ret = this.beforeMemoryUsedMB;
        return ret;
    }

    @ElVoField(physicalName = "beforeMemoryUsedMB")
    public void setBeforeMemoryUsedMB(String beforeMemoryUsedMB){
        this.beforeMemoryUsedMB = beforeMemoryUsedMB;
    }

    @ElVoField(physicalName = "beforeFileId")
    public String getBeforeFileId(){
        String ret = this.beforeFileId;
        return ret;
    }

    @ElVoField(physicalName = "beforeFileId")
    public void setBeforeFileId(String beforeFileId){
        this.beforeFileId = beforeFileId;
    }

    @ElVoField(physicalName = "afterMethodName")
    public String getAfterMethodName(){
        String ret = this.afterMethodName;
        return ret;
    }

    @ElVoField(physicalName = "afterMethodName")
    public void setAfterMethodName(String afterMethodName){
        this.afterMethodName = afterMethodName;
    }

    @ElVoField(physicalName = "afterDescription")
    public String getAfterDescription(){
        String ret = this.afterDescription;
        return ret;
    }

    @ElVoField(physicalName = "afterDescription")
    public void setAfterDescription(String afterDescription){
        this.afterDescription = afterDescription;
    }

    @ElVoField(physicalName = "afterDuration")
    public String getAfterDuration(){
        String ret = this.afterDuration;
        return ret;
    }

    @ElVoField(physicalName = "afterDuration")
    public void setAfterDuration(String afterDuration){
        this.afterDuration = afterDuration;
    }

    @ElVoField(physicalName = "afterDurationSeconds")
    public String getAfterDurationSeconds(){
        String ret = this.afterDurationSeconds;
        return ret;
    }

    @ElVoField(physicalName = "afterDurationSeconds")
    public void setAfterDurationSeconds(String afterDurationSeconds){
        this.afterDurationSeconds = afterDurationSeconds;
    }

    @ElVoField(physicalName = "afterThroughput")
    public String getAfterThroughput(){
        String ret = this.afterThroughput;
        return ret;
    }

    @ElVoField(physicalName = "afterThroughput")
    public void setAfterThroughput(String afterThroughput){
        this.afterThroughput = afterThroughput;
    }

    @ElVoField(physicalName = "afterMemoryUsedMB")
    public String getAfterMemoryUsedMB(){
        String ret = this.afterMemoryUsedMB;
        return ret;
    }

    @ElVoField(physicalName = "afterMemoryUsedMB")
    public void setAfterMemoryUsedMB(String afterMemoryUsedMB){
        this.afterMemoryUsedMB = afterMemoryUsedMB;
    }

    @ElVoField(physicalName = "afterFileId")
    public String getAfterFileId(){
        String ret = this.afterFileId;
        return ret;
    }

    @ElVoField(physicalName = "afterFileId")
    public void setAfterFileId(String afterFileId){
        this.afterFileId = afterFileId;
    }

    @ElVoField(physicalName = "speedImprovement")
    public String getSpeedImprovement(){
        String ret = this.speedImprovement;
        return ret;
    }

    @ElVoField(physicalName = "speedImprovement")
    public void setSpeedImprovement(String speedImprovement){
        this.speedImprovement = speedImprovement;
    }

    @ElVoField(physicalName = "throughputImprovement")
    public String getThroughputImprovement(){
        String ret = this.throughputImprovement;
        return ret;
    }

    @ElVoField(physicalName = "throughputImprovement")
    public void setThroughputImprovement(String throughputImprovement){
        this.throughputImprovement = throughputImprovement;
    }

    @ElVoField(physicalName = "memoryEfficiency")
    public String getMemoryEfficiency(){
        String ret = this.memoryEfficiency;
        return ret;
    }

    @ElVoField(physicalName = "memoryEfficiency")
    public void setMemoryEfficiency(String memoryEfficiency){
        this.memoryEfficiency = memoryEfficiency;
    }

    @ElVoField(physicalName = "speedupFactor")
    public String getSpeedupFactor(){
        String ret = this.speedupFactor;
        return ret;
    }

    @ElVoField(physicalName = "speedupFactor")
    public void setSpeedupFactor(String speedupFactor){
        this.speedupFactor = speedupFactor;
    }

    @ElVoField(physicalName = "memoryAdvantage")
    public String getMemoryAdvantage(){
        String ret = this.memoryAdvantage;
        return ret;
    }

    @ElVoField(physicalName = "memoryAdvantage")
    public void setMemoryAdvantage(String memoryAdvantage){
        this.memoryAdvantage = memoryAdvantage;
    }

    @ElVoField(physicalName = "speedAdvantage")
    public String getSpeedAdvantage(){
        String ret = this.speedAdvantage;
        return ret;
    }

    @ElVoField(physicalName = "speedAdvantage")
    public void setSpeedAdvantage(String speedAdvantage){
        this.speedAdvantage = speedAdvantage;
    }

    @ElVoField(physicalName = "scalabilityAdvantage")
    public String getScalabilityAdvantage(){
        String ret = this.scalabilityAdvantage;
        return ret;
    }

    @ElVoField(physicalName = "scalabilityAdvantage")
    public void setScalabilityAdvantage(String scalabilityAdvantage){
        this.scalabilityAdvantage = scalabilityAdvantage;
    }

    @ElVoField(physicalName = "conclusion")
    public String getConclusion(){
        String ret = this.conclusion;
        return ret;
    }

    @ElVoField(physicalName = "conclusion")
    public void setConclusion(String conclusion){
        this.conclusion = conclusion;
    }

    @ElVoField(physicalName = "stabilityAdvantage")
    public String getStabilityAdvantage(){
        String ret = this.stabilityAdvantage;
        return ret;
    }

    @ElVoField(physicalName = "stabilityAdvantage")
    public void setStabilityAdvantage(String stabilityAdvantage){
        this.stabilityAdvantage = stabilityAdvantage;
    }

    @ElVoField(physicalName = "errorMessage")
    public String getErrorMessage(){
        String ret = this.errorMessage;
        return ret;
    }

    @ElVoField(physicalName = "errorMessage")
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PerformanceComparisonResultVo [");
        sb.append("success").append("=").append(success).append(",");
        sb.append("demoTitle").append("=").append(demoTitle).append(",");
        sb.append("fileName").append("=").append(fileName).append(",");
        sb.append("fileSizeMB").append("=").append(fileSizeMB).append(",");
        sb.append("testTimestamp").append("=").append(testTimestamp).append(",");
        sb.append("systemCpuCores").append("=").append(systemCpuCores).append(",");
        sb.append("systemMaxMemoryMB").append("=").append(systemMaxMemoryMB).append(",");
        sb.append("beforeMethodName").append("=").append(beforeMethodName).append(",");
        sb.append("beforeDescription").append("=").append(beforeDescription).append(",");
        sb.append("beforeDuration").append("=").append(beforeDuration).append(",");
        sb.append("beforeDurationSeconds").append("=").append(beforeDurationSeconds).append(",");
        sb.append("beforeThroughput").append("=").append(beforeThroughput).append(",");
        sb.append("beforeMemoryUsedMB").append("=").append(beforeMemoryUsedMB).append(",");
        sb.append("beforeFileId").append("=").append(beforeFileId).append(",");
        sb.append("afterMethodName").append("=").append(afterMethodName).append(",");
        sb.append("afterDescription").append("=").append(afterDescription).append(",");
        sb.append("afterDuration").append("=").append(afterDuration).append(",");
        sb.append("afterDurationSeconds").append("=").append(afterDurationSeconds).append(",");
        sb.append("afterThroughput").append("=").append(afterThroughput).append(",");
        sb.append("afterMemoryUsedMB").append("=").append(afterMemoryUsedMB).append(",");
        sb.append("afterFileId").append("=").append(afterFileId).append(",");
        sb.append("speedImprovement").append("=").append(speedImprovement).append(",");
        sb.append("throughputImprovement").append("=").append(throughputImprovement).append(",");
        sb.append("memoryEfficiency").append("=").append(memoryEfficiency).append(",");
        sb.append("speedupFactor").append("=").append(speedupFactor).append(",");
        sb.append("memoryAdvantage").append("=").append(memoryAdvantage).append(",");
        sb.append("speedAdvantage").append("=").append(speedAdvantage).append(",");
        sb.append("scalabilityAdvantage").append("=").append(scalabilityAdvantage).append(",");
        sb.append("conclusion").append("=").append(conclusion).append(",");
        sb.append("stabilityAdvantage").append("=").append(stabilityAdvantage).append(",");
        sb.append("errorMessage").append("=").append(errorMessage);
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
