package com.qingtian.lcpes.base.file.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public class FileInfoVO implements Serializable {
    private String fileId;
    private byte[] contents;
    private String bufferContent;
    private String fileName;
    private String group;
    private String filePath;
    private String viewUrl;
    private String fileMd5;
    private String type;
    private Long size;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime uploadTime;
    private String uploadingName;

    public FileInfoVO() {
    }

    public String getFileId() {
        return this.fileId;
    }

    public byte[] getContents() {
        return this.contents;
    }

    public String getBufferContent() {
        return this.bufferContent;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getGroup() {
        return this.group;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getViewUrl() {
        return this.viewUrl;
    }

    public String getFileMd5() {
        return this.fileMd5;
    }

    public String getType() {
        return this.type;
    }

    public Long getSize() {
        return this.size;
    }

    public LocalDateTime getUploadTime() {
        return this.uploadTime;
    }

    public String getUploadingName() {
        return this.uploadingName;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public void setBufferContent(String bufferContent) {
        this.bufferContent = bufferContent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setUploadingName(String uploadingName) {
        this.uploadingName = uploadingName;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (!(o instanceof FileInfoVO)) {
            return false;
        }
        else {
            FileInfoVO other = (FileInfoVO) o;
            if (!other.canEqual(this)) {
                return false;
            }
            else {
                Object this$size = this.getSize();
                Object other$size = other.getSize();
                if (this$size == null) {
                    if (other$size != null) {
                        return false;
                    }
                }
                else if (!this$size.equals(other$size)) {
                    return false;
                }

                Object this$fileId = this.getFileId();
                Object other$fileId = other.getFileId();
                if (this$fileId == null) {
                    if (other$fileId != null) {
                        return false;
                    }
                }
                else if (!this$fileId.equals(other$fileId)) {
                    return false;
                }

                if (!Arrays.equals(this.getContents(), other.getContents())) {
                    return false;
                }
                else {
                    Object this$bufferContent = this.getBufferContent();
                    Object other$bufferContent = other.getBufferContent();
                    if (this$bufferContent == null) {
                        if (other$bufferContent != null) {
                            return false;
                        }
                    }
                    else if (!this$bufferContent.equals(other$bufferContent)) {
                        return false;
                    }

                    label125:
                    {
                        Object this$fileName = this.getFileName();
                        Object other$fileName = other.getFileName();
                        if (this$fileName == null) {
                            if (other$fileName == null) {
                                break label125;
                            }
                        }
                        else if (this$fileName.equals(other$fileName)) {
                            break label125;
                        }

                        return false;
                    }

                    label118:
                    {
                        Object this$group = this.getGroup();
                        Object other$group = other.getGroup();
                        if (this$group == null) {
                            if (other$group == null) {
                                break label118;
                            }
                        }
                        else if (this$group.equals(other$group)) {
                            break label118;
                        }

                        return false;
                    }

                    Object this$filePath = this.getFilePath();
                    Object other$filePath = other.getFilePath();
                    if (this$filePath == null) {
                        if (other$filePath != null) {
                            return false;
                        }
                    }
                    else if (!this$filePath.equals(other$filePath)) {
                        return false;
                    }

                    label104:
                    {
                        Object this$viewUrl = this.getViewUrl();
                        Object other$viewUrl = other.getViewUrl();
                        if (this$viewUrl == null) {
                            if (other$viewUrl == null) {
                                break label104;
                            }
                        }
                        else if (this$viewUrl.equals(other$viewUrl)) {
                            break label104;
                        }

                        return false;
                    }

                    Object this$fileMd5 = this.getFileMd5();
                    Object other$fileMd5 = other.getFileMd5();
                    if (this$fileMd5 == null) {
                        if (other$fileMd5 != null) {
                            return false;
                        }
                    }
                    else if (!this$fileMd5.equals(other$fileMd5)) {
                        return false;
                    }

                    label90:
                    {
                        Object this$type = this.getType();
                        Object other$type = other.getType();
                        if (this$type == null) {
                            if (other$type == null) {
                                break label90;
                            }
                        }
                        else if (this$type.equals(other$type)) {
                            break label90;
                        }

                        return false;
                    }

                    Object this$uploadTime = this.getUploadTime();
                    Object other$uploadTime = other.getUploadTime();
                    if (this$uploadTime == null) {
                        if (other$uploadTime != null) {
                            return false;
                        }
                    }
                    else if (!this$uploadTime.equals(other$uploadTime)) {
                        return false;
                    }

                    Object this$uploadingName = this.getUploadingName();
                    Object other$uploadingName = other.getUploadingName();
                    if (this$uploadingName == null) {
                        if (other$uploadingName == null) {
                            return true;
                        }
                    }
                    else if (this$uploadingName.equals(other$uploadingName)) {
                        return true;
                    }

                    return false;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof FileInfoVO;
    }

    public int hashCode() {
        int result = 1;
        Object $size = this.getSize();
        result = result * 59 + ($size == null ? 43 : $size.hashCode());
        Object $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        result = result * 59 + Arrays.hashCode(this.getContents());
        Object $bufferContent = this.getBufferContent();
        result = result * 59 + ($bufferContent == null ? 43 : $bufferContent.hashCode());
        Object $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        Object $group = this.getGroup();
        result = result * 59 + ($group == null ? 43 : $group.hashCode());
        Object $filePath = this.getFilePath();
        result = result * 59 + ($filePath == null ? 43 : $filePath.hashCode());
        Object $viewUrl = this.getViewUrl();
        result = result * 59 + ($viewUrl == null ? 43 : $viewUrl.hashCode());
        Object $fileMd5 = this.getFileMd5();
        result = result * 59 + ($fileMd5 == null ? 43 : $fileMd5.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $uploadTime = this.getUploadTime();
        result = result * 59 + ($uploadTime == null ? 43 : $uploadTime.hashCode());
        Object $uploadingName = this.getUploadingName();
        result = result * 59 + ($uploadingName == null ? 43 : $uploadingName.hashCode());
        return result;
    }

    public String toString() {
        return "FileInfoVO(fileId=" + this.getFileId() + ", contents=" + Arrays.toString(this.getContents()) + ", bufferContent=" + this.getBufferContent() + ", fileName=" + this.getFileName() + ", group=" + this.getGroup() + ", filePath=" + this.getFilePath() + ", viewUrl=" + this.getViewUrl() + ", fileMd5=" + this.getFileMd5() + ", type=" + this.getType() + ", size=" + this.getSize() + ", uploadTime=" + this.getUploadTime() + ", uploadingName=" + this.getUploadingName() + ")";
    }
}