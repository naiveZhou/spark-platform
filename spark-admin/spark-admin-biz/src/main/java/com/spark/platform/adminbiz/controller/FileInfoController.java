package com.spark.platform.adminbiz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.dto.FileInfoDTO;
import com.spark.platform.adminapi.entity.file.FileInfo;
import com.spark.platform.adminbiz.service.file.FileInfoService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.spark.platform.common.base.support.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 文件信息表 前端控制器
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传")
@AllArgsConstructor
public class FileInfoController extends BaseController {

    private final FileInfoService fileInfoService;

    @PostMapping(value = "/upload",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件")
    public ApiResponse<FileInfo> upload(@RequestParam MultipartFile file) {
        return success(fileInfoService.upload(file));
    }

    @PatchMapping
    @ApiOperation(value = "绑定文件")
    public ApiResponse bind(@RequestBody FileInfoDTO fileInfoDTO) {
        fileInfoService.bindFile(fileInfoDTO);
        return success("绑定成功");
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse<IPage> page(Page page, FileInfo fileInfo) {
        return success(fileInfoService.findPage(page, fileInfo));
    }

    @GetMapping("/biz")
    @ApiOperation(value = "业务查询")
    public ApiResponse<List<FileInfo>> findByBiz(@RequestParam String bizId, @RequestParam String bizType) {
        return success(fileInfoService.findByBiz(bizId, bizType));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "业务查询")
    public ApiResponse<FileInfo> getById(@PathVariable Long id) {
        return success(fileInfoService.getById(id));
    }

    @GetMapping("/api/{id}")
    @ApiOperation(value = "下载文件")
    public void download(@PathVariable Long id, HttpServletResponse response) {
        fileInfoService.downloadFile(id, response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文件")
    public ApiResponse<Boolean> delete(@PathVariable Long id){
        return success(fileInfoService.removeById(id));
    }

}
