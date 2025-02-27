package cn.buk.common.util;

import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;


public class StringUtil {

    /**
     * emoji表情替换
     *
     * @param source 原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source,String slipStr) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }

    /**
     * 生成自动合并的自动审核备注
     */
    public static String mergedRemark(@NotNull String oldRemark, @NotNull String autoAuditRemark) {
        if (oldRemark.contains("#")) {
            return oldRemark.substring(0, oldRemark.indexOf("#")) + "#" + autoAuditRemark;
        } else {
            return oldRemark + "#" + autoAuditRemark;
        }
    }
}
