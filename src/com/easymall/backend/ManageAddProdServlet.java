package com.easymall.backend;

import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;
import com.easymall.service.ProdServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ManageAddProdServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            //1.上传商品图片
            String upload = this.getServletContext().getRealPath("WEB-INF/upload");
            String temp = this.getServletContext().getRealPath("WEB-INF/temp");
            //--获取文件上传工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 100);
            factory.setRepository(new File(temp));
            //--获取上传文件核心类
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //----检查当前表单是否使一个正确的文件上传表单
            if (!fileUpload.isMultipartContent(request))
            {
                throw new RuntimeException("请使用正确的表单进行文件上传！");
            }
            //----设定编码解决文件名乱码
            fileUpload.setHeaderEncoding("utf-8");
            //----单个文件大小不超过1M
            fileUpload.setFileSizeMax(1024 * 1024);
            //----总文件大小不超过1M
            fileUpload.setSizeMax(1024 * 1024);
            //--解析request获取FileItem组成的集合
            List<FileItem> list = fileUpload.parseRequest(request);
            //--遍历处理
            Map<String, String> paramMap = new HashMap<String, String>();
            for (FileItem item : list)
            {
                if (item.isFormField())
                {
                    //普通字段项
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
                    paramMap.put(name, value);
                } else
                {
                    //文件上传项
                    String fname = item.getName();
                    //部分ie版本文件名有问题
                    if (fname.contains("\\"))
                    {
                        fname = fname.substring(fname.lastIndexOf("\\") + 1);
                    }
                    //防止文件名重复
                    String uuidname = UUID.randomUUID().toString() + "_" + fname;
                    //分目录存储
                    String hash = Integer.toHexString(uuidname.hashCode());
                    for (int i = 0; i < (8 - hash.length()); i++)
                    {
                        hash = "0" + hash;
                    }
                    String imgurl = "/WEB-INF/upload";
                    for (char c : hash.toCharArray())
                    {
                        upload = upload + "/" + c;
                        imgurl = imgurl + "/" + c;
                    }
                    new File(upload).mkdirs();
                    paramMap.put("imgurl", imgurl + "/" + uuidname);
                    //流对接上传文件
                    InputStream in = item.getInputStream();
                    OutputStream out = new FileOutputStream(upload + "/" + uuidname);
                    byte[] bs = new byte[1024];
                    int i = 0;
                    while ((i = in.read(bs)) != -1)
                    {
                        out.write(bs, 0, i);
                    }
                    in.close();
                    out.close();
                    //--删除临时文件
                    item.delete();

                }
            }
            //2.获取提交的商品信息，调用Service增加商品
            //--将商品信息封装到javabean
            Product prod = new Product();
            BeanUtils.populate(prod, paramMap);
            prod.setId(UUID.randomUUID().toString());
            //--调用Service增加商品信息
            ProdService service = BasicFactory.getFactory().getInstance(ProdServiceImpl.class);
            service.addProd(prod);
            //3.回到管理商品列表页面
            response.sendRedirect(request.getContextPath() + "/servlet/ManageProdListServlet");
        } catch (FileUploadBase.FileSizeLimitExceededException e)
        {
            throw new RuntimeException("单个文件大小不超过1M！总文件不超过1M！");
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
