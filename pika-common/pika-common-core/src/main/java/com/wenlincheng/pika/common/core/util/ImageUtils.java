package com.wenlincheng.pika.common.core.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wenlincheng.pika.common.core.enums.ImageTypeEnum;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 二维码以及图片合成工具类
 *
 * @author : wenlincheng
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class ImageUtils {


    public static BufferedImage enQRCode(String contents, int width, int height) throws WriterException {
        //定义二维码参数
        final Map<EncodeHintType, Object> hints = new HashMap(8) {
            {
                //编码
                put(EncodeHintType.CHARACTER_SET, "UTF-8");
                //容错级别
                put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                //边距
                put(EncodeHintType.MARGIN, 0);
            }
        };
        return enQRCode(contents, width, height, hints);
    }


    /**
     * 生成二维码
     *
     * @param contents 二维码内容
     * @param width    图片宽度
     * @param height   图片高度
     * @param hints    二维码相关参数
     * @return BufferedImage对象
     * @throws WriterException 编码时出错
     * @throws IOException     写入文件出错
     */
    public static BufferedImage enQRCode(String contents, int width, int height, Map hints) throws WriterException {
        //生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


    /**
     * 将图片绘制在背景图上
     *
     * @param backgroundPath 背景图路径
     * @param upImage     图片
     * @param x              图片在背景图上绘制的x轴起点
     * @param y              图片在背景图上绘制的y轴起点
     * @return
     */
    public static BufferedImage drawImage(String backgroundPath, BufferedImage upImage, int x, int y) throws IOException {
        //读取背景图的图片流
        BufferedImage backgroundImage;
        //Try-with-resources 资源自动关闭,会自动调用close()方法关闭资源,只限于实现Closeable或AutoCloseable接口的类
        try (InputStream imagein = new FileInputStream(backgroundPath)) {
            backgroundImage = ImageIO.read(imagein);
        }
        return drawImage(backgroundImage, upImage, x, y);
    }

    /**
     * 将图片绘制在背景图上
     *
     * @param backgroundPath 背景图路径
     * @param upPath         上层图路径
     * @param x              图片在背景图上绘制的x轴起点
     * @param y              图片在背景图上绘制的y轴起点
     * @return
     */
    public static BufferedImage drawImage(String backgroundPath, String upPath, int x, int y) throws IOException {
        //读取背景图的图片流
        BufferedImage backgroundImage;
        BufferedImage upImage;
        //Try-with-resources 资源自动关闭,会自动调用close()方法关闭资源,只限于实现Closeable或AutoCloseable接口的类
        InputStream backIn = new FileInputStream(backgroundPath);
        backgroundImage = ImageIO.read(backIn);
        InputStream upIn = new FileInputStream(upPath);
        upImage = ImageIO.read(upIn);
        return drawImage(backgroundImage, upImage, x, y);
    }


    /**
     * 将图片绘制在背景图上
     *
     * @param backgroundImage 背景图
     * @param upImage      图片
     * @param x               图片在背景图上绘制的x轴起点
     * @param y               图片在背景图上绘制的y轴起点
     * @return
     * @throws IOException
     */
    public static BufferedImage drawImage(BufferedImage backgroundImage, BufferedImage upImage, int x, int y) throws IOException {
        Objects.requireNonNull(backgroundImage, "背景图不可为空");
        Objects.requireNonNull(upImage, "图片不可为空");
        //合并图片
        Graphics2D g = backgroundImage.createGraphics();
        g.drawImage(upImage, x, y, upImage.getWidth(), upImage.getHeight(), null);
        return backgroundImage;
    }

    /**
     * 将文字绘制在背景图上
     *
     * @param backgroundImage 背景图
     * @param x               文字在背景图上绘制的x轴起点
     * @param y               文字在背景图上绘制的y轴起点
     * @return
     * @throws IOException
     */
    public static BufferedImage drawString(BufferedImage backgroundImage, String text, int x, int y, Font font, Color color) {
        //绘制文字
        Graphics2D g = backgroundImage.createGraphics();
        //设置颜色
        g.setColor(color);
        //消除锯齿状
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        //设置字体
        g.setFont(font);
        //绘制文字
        g.drawString(text, x, y);
        return backgroundImage;
    }


    public static InputStream bufferedImageToInputStream(BufferedImage backgroundImage) throws IOException {
        return bufferedImageToInputStream(backgroundImage, "png");
    }

    /**
     * backgroundImage 转换为输出流
     *
     * @param backgroundImage
     * @param format
     * @return
     * @throws IOException
     */
    public static InputStream bufferedImageToInputStream(BufferedImage backgroundImage, String format) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try (
                ImageOutputStream imOut = ImageIO.createImageOutputStream(bs)) {
            ImageIO.write(backgroundImage, format, imOut);
            InputStream is = new ByteArrayInputStream(bs.toByteArray());
            return is;
        }
    }


    /**
     * 按比例对图片进行缩放
     *
     * @param oldImg 图片
     * @param scale  缩放比例
     * @throws IOException
     */
    public static BufferedImage zoomByScale(BufferedImage oldImg,  double scale) throws IOException {
        //获取缩放后的长和宽

        int width = (int) (scale * oldImg.getWidth());
        int height = (int) (scale * oldImg.getHeight());
        //获取缩放后的Image对象
        Image img = oldImg.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        //新建一个和Image对象相同大小的画布
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics = newImg.createGraphics();
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(img, 0, 0, null);
        //释放资源
        graphics.dispose();

        return newImg;
    }

    /**
     * 指定长和宽对图片进行缩放
     *
     * @param oldImage  图片
     * @param width     长
     * @param height    宽
     * @throws IOException
     */
    public static BufferedImage zoomBySize(BufferedImage oldImage, String type, int width, int height) {
        //与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
        Image img = oldImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage newImage;
        if (ImageTypeEnum.PNG.getDesc().equals(type)){
            newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }else {
            newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();
        return newImage;
    }


    /**
     * 图片设置圆角
     *
     * @param srcImage
     * @param radius
     * @param border
     * @param padding
     * @return
     * @throws IOException
     */
    @SuppressWarnings("all")
    public static BufferedImage setRadius(BufferedImage srcImage, int radius, int border, int padding) throws IOException{
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();

        int canvasWidth = width + padding * 2;
        int canvasHeight = height + padding * 2;

        BufferedImage image = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, srcImage.getWidth(),
                srcImage.getHeight());

        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(srcImage.getWidth(), srcImage.getHeight(), Transparency.TRANSLUCENT);
        g2 = image.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0,srcImage.getWidth(), srcImage.getHeight(), radius, radius);
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(srcImage, 0, 0, srcImage.getWidth(), srcImage.getHeight(), null);

        if(border !=0){
            // gs.setColor(Color.GRAY);
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(border));
            g2.drawRoundRect(padding, padding, canvasWidth  * padding, canvasHeight * padding, radius, radius);
        }
        g2.dispose();

        return  image;

    }

    /**
     * 保存为文件
     *
     * @param is
     * @param fileName
     * @throws IOException
     */
    public static void saveFile(InputStream is, String fileName) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(is);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        }
    }

//    public static void main(String[] args) {
//        //二维码宽度
//        int width = 293;
//        //二维码高度
//        int height = 293;
//        //二维码内容
//        String content = "http://baidu.com";
//        BufferedImage upImage = null;
//        try {
//            //二维码图片流
//            upImage = ImageUtils.enQRCode(content, width, height);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        //背景图片地址
//        String backgroundPath = "/Users/zl/Desktop/back.png";
//        String codePath = "/Users/zl/Desktop/code.png";
//        InputStream inputStream = null;
//        try {
//            //合成二维码和背景图
//            BufferedImage image = ImageUtils.drawImage(backgroundPath, upImage, 224, 754);
//            //绘制文字
//            Font font = new Font("微软雅黑", Font.BOLD, 35);
//            String text = "17000";
//            image = ImageUtils.drawString(image, text, 375, 647, font, new Color(244, 254, 189));
//            //图片转inputStream
//            inputStream = ImageUtils.bufferedImageToInputStream(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //保存的图片路径
//        String originalFileName = "/Users/zl/Desktop/target.png";
//        try {
//            //保存为本地图片
//            ImageUtils.saveFile(inputStream, originalFileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


