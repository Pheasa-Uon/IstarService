package com.istar.service.dto.Administrator.UsersManagement;

public class FeaturePermissionDTO {

    private Long roleId;
    private Long featureId;

    private Boolean isSearch;
    private Boolean isAdd;
    private Boolean isViewed;
    private Boolean isEdit;
    private Boolean isApprove;
    private Boolean isReject;
    private Boolean isDeleted;
    private Boolean isSave;
    private Boolean isClear;
    private Boolean isCancel;
    private Boolean isProcess;
    private Boolean isImport;
    private Boolean isExport;

    private Boolean bStatus;

    // Constructors
    public FeaturePermissionDTO() {}

    public FeaturePermissionDTO(Long roleId, Long featureId,
                                Boolean isSearch, Boolean isAdd, Boolean isViewed, Boolean isEdit,
                                Boolean isApprove, Boolean isReject, Boolean isDeleted, Boolean isSave,
                                Boolean isClear, Boolean isCancel, Boolean isProcess,
                                Boolean isImport, Boolean isExport, Boolean bStatus) {
        this.roleId = roleId;
        this.featureId = featureId;
        this.isSearch = isSearch;
        this.isAdd = isAdd;
        this.isViewed = isViewed;
        this.isEdit = isEdit;
        this.isApprove = isApprove;
        this.isReject = isReject;
        this.isDeleted = isDeleted;
        this.isSave = isSave;
        this.isClear = isClear;
        this.isCancel = isCancel;
        this.isProcess = isProcess;
        this.isImport = isImport;
        this.isExport = isExport;
        this.bStatus = bStatus;
    }

    // Getters and Setters
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public Long getFeatureId() { return featureId; }
    public void setFeatureId(Long featureId) { this.featureId = featureId; }

    public Boolean getIsSearch() { return isSearch; }
    public void setIsSearch(Boolean isSearch) { this.isSearch = isSearch; }

    public Boolean getIsAdd() { return isAdd; }
    public void setIsAdd(Boolean isAdd) { this.isAdd = isAdd; }

    public Boolean getIsViewed() { return isViewed; }
    public void setIsViewed(Boolean isViewed) { this.isViewed = isViewed; }

    public Boolean getIsEdit() { return isEdit; }
    public void setIsEdit(Boolean isEdit) { this.isEdit = isEdit; }

    public Boolean getIsApprove() { return isApprove; }
    public void setIsApprove(Boolean isApprove) { this.isApprove = isApprove; }

    public Boolean getIsReject() { return isReject; }
    public void setIsReject(Boolean isReject) { this.isReject = isReject; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }

    public Boolean getIsSave() { return isSave; }
    public void setIsSave(Boolean isSave) { this.isSave = isSave; }

    public Boolean getIsClear() { return isClear; }
    public void setIsClear(Boolean isClear) { this.isClear = isClear; }

    public Boolean getIsCancel() { return isCancel; }
    public void setIsCancel(Boolean isCancel) { this.isCancel = isCancel; }

    public Boolean getIsProcess() { return isProcess; }
    public void setIsProcess(Boolean isProcess) { this.isProcess = isProcess; }

    public Boolean getIsImport() { return isImport; }
    public void setIsImport(Boolean isImport) { this.isImport = isImport; }

    public Boolean getIsExport() { return isExport; }
    public void setIsExport(Boolean isExport) { this.isExport = isExport; }

    public Boolean getBStatus() { return bStatus; }
    public void setBStatus(Boolean bStatus) { this.bStatus = bStatus; }
}
