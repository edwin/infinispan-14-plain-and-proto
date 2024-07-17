package com.edw.controller;

import com.edw.helper.GenerateCacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *  com.edw.controller.IndexController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Jul 2024 11:41
 */
@RestController
public class IndexController {

    @Autowired
    private GenerateCacheHelper generateCacheHelper;

    @GetMapping(path = "/plain")
    public String initPlain() {
        return generateCacheHelper.generatePlainText() + " \r\n";
    }

    @GetMapping(path = "/proto")
    public String initProto() {
        return generateCacheHelper.generateProto() + " \r\n";
    }
}
