package cn.buk.util;

import cn.buk.common.util.StringUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

  @Test
  void test_mergedRemark_1() {
    final String oldRemark = "abc";
    final String appendRemark = "def";
    final String newRemark = "abc#def";

    assertEquals(newRemark, StringUtil.mergedRemark(oldRemark, appendRemark));
  }

  @Test
  void test_mergedRemark_2() {
    final String oldRemark = "abc#123#123";
    final String appendRemark = "def";
    final String newRemark = "abc#def";

    assertEquals(newRemark, StringUtil.mergedRemark(oldRemark, appendRemark));
  }
}