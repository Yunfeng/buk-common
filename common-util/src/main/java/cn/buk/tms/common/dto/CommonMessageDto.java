package cn.buk.tms.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 通用消息DTO，用于jms的消息分发
 * @author yfdai
 */
public class CommonMessageDto<T> {

    /**
     * 版本
     */
    private int version;

    /**
     * 消息类型
     * 1 - 航班查询次数统计信息
     */
    private int msgType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> list;

    /**
     * 消息的时间戳
     */
    private long createTime;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
