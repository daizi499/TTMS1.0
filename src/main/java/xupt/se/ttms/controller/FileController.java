package xupt.se.ttms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xupt.se.ttms.common.R;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件上传接口
 * 支持图片上传，保存到本地磁盘并通过URL访问
 */
@RestController
@RequestMapping("/api/upload")
public class FileController {

    // 上传目录，从配置文件读取，默认为项目根目录下的 uploads
    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    /**
     * 上传图片文件
     * POST /api/upload/image
     * 返回可访问的URL路径
     */
    @PostMapping("/image")
    public R uploadImage(@RequestParam("file") MultipartFile file) {
        // 1. 检查文件是否为空
        if (file.isEmpty()) {
            return R.error("请选择要上传的文件");
        }

        // 2. 检查文件类型（只允许图片）
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return R.error("只允许上传图片文件");
        }

        // 3. 生成唯一文件名：日期/原始名_UUID.扩展名
        String originalName = file.getOriginalFilename();
        String ext = "";
        if (originalName != null && originalName.contains(".")) {
            ext = originalName.substring(originalName.lastIndexOf("."));
        }
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String newFileName = UUID.randomUUID().toString().substring(0, 8) + ext;
        String relativePath = datePath + "/" + newFileName;  // 如：20260526/a1b2c3d4.jpg

        try {
            // 4. 构建目标目录和文件（使用绝对路径）
            File dir = new File(uploadDir, datePath);
            if (!dir.exists()) {
                dir.mkdirs();  // 递归创建目录
            }
            File destFile = new File(dir, newFileName);

            // 5. 保存文件到磁盘
            file.transferTo(destFile);

            // 6. 返回可访问的URL路径
            String url = "/uploads/" + relativePath;
            return R.ok("上传成功", url);

        } catch (IOException e) {
            e.printStackTrace();
            return R.error("文件上传失败：" + e.getMessage());
        }
    }
}
