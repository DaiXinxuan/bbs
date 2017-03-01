package com.cn.bbs.util;

import junit.framework.TestCase;

/**
 * Created by dxx on 2017/2/27.
 */
public class MD5UtilTest extends TestCase {
    public void testMd5Encode() throws Exception {
        String MD5Pass = MD5Util.md5Encode("");
        System.out.println(MD5Pass);
    }

}