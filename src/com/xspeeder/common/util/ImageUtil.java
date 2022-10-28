
package com.xspeeder.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;
import com.xspeeder.common.exception.XException;



public class ImageUtil {

    public static int COVER_W = 256;
    public static int COVER_H = 256;
    
    public static int PHOTO_IMG_WIDTH = 256;
    public static int PHOTO_IMG_HEIGHT = 256;

    
    public static int IDCARD_IMG_WIDTH = 360;
    public static int IDCARD_IMG_HEIGHT = 240;

    public static String ERR_FILE_UPLOAD = "ERR_FILE_UPLOAD";

    
    public static void resizePhotoImage(String src_image,
                                        String dest_image)
            throws XException {
        resizeImage(src_image, dest_image, PHOTO_IMG_WIDTH, PHOTO_IMG_HEIGHT);
    }

    
    public static void resizeIDCardImage(String src_image,
                                         String dest_image)
            throws XException {
        resizeImage(src_image, dest_image, IDCARD_IMG_WIDTH, IDCARD_IMG_HEIGHT);
    }

    public static int readImageOrientation(String imagePath) {
        File imageFile = new File(imagePath);
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(imageFile);
            ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);

            int orientation = 1;
            try {
                if (directory != null)
                    orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return orientation;

        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, Integer width, Integer height) {
        int type = (originalImage.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);

        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        return resizedImage;
    }

    
    public static BufferedImage rotateImage(BufferedImage originalImage, int angle) {
        /*
        AffineTransform tx = new AffineTransform();
	    tx.rotate(Math.toRadians(angle), originalImage.getWidth() / 2, originalImage.getHeight() / 2);

	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	    BufferedImage resizedImage = op.filter(originalImage, null);
	    return resizedImage;
	    */

        int type = (originalImage.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        int nw = originalImage.getWidth(), nh = originalImage.getHeight();
        double x = 0, y = 0;
        if (angle % 90 == 0) {
            nw = originalImage.getHeight();
            nh = originalImage.getWidth();

            x = (originalImage.getHeight() - originalImage.getWidth()) / 2.0;
            y = (originalImage.getWidth() - originalImage.getHeight()) / 2.0;
        }

        BufferedImage resizedImage = new BufferedImage(nw, nh, type);
        Graphics2D g = resizedImage.createGraphics();
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), originalImage.getWidth() / 2, originalImage.getHeight() / 2);
        g.drawImage(originalImage, at, null);

        return resizedImage;

    }

    
    public static void resizeImage(String src_image,
                                   String dest_image,
                                   int width,
                                   int height)
            throws XException {
        try {
            if (!FileUtil.existFile(src_image)) {
                return;
            }

            File src_file = new File(src_image);
            BufferedImage originalImage = ImageIO.read(src_file);


            String fileName = src_file.getName();
            int pos_dot = fileName.lastIndexOf('.');
            String extension = fileName.substring(pos_dot + 1);

            ImageIO.write(ImageUtil.resizeImage(originalImage, width, height), extension, new File(dest_image));

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            FileUtil.deleteFile(src_image);
            throw new XException(ERR_FILE_UPLOAD);
        } catch (Exception ex) {
            ex.printStackTrace();
            FileUtil.deleteFile(dest_image);
            throw new XException(ERR_FILE_UPLOAD);
        }
    }

    
    public static void resizeImageForPlugin(String src_image,
                                            String dest_image,
                                            int maxwidth,
                                            int maxheight)
            throws XException {
        try {
            if (!FileUtil.existFile(src_image)) {
                return;
            }

            File src_file = new File(src_image);
            BufferedImage originalImage = ImageIO.read(src_file);

            int orient = readImageOrientation(src_image);


            
            String fileName = src_file.getName();
            int pos_dot = fileName.lastIndexOf('.');
            String extension = fileName.substring(pos_dot + 1);

            int w = originalImage.getWidth();
            int h = originalImage.getHeight();

            if (w > maxwidth) {
                double ratio = (double) maxwidth / (double) w;
                h = (int) ((double) h * ratio);
                w = maxwidth;
            } else {
                double ratio = (double) maxheight / (double) h;
                w = (int) ((double) w * ratio);
                h = maxheight;
            }

            BufferedImage img = resizeImage(originalImage, w, h);
            if (orient == 3) {
                img = rotateImage(img, 180);
            } else if (orient == 6) {
                img = rotateImage(img, 90);
            } else if (orient == 8) {
                img = rotateImage(img, -90);
            }
            ImageIO.write(img, extension, new File(dest_image));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            FileUtil.deleteFile(src_image);
            throw new XException(ERR_FILE_UPLOAD);
        } catch (Exception ex) {
            ex.printStackTrace();
            FileUtil.deleteFile(dest_image);
            throw new XException(ERR_FILE_UPLOAD);
        }
    }

    
    public static BufferedImage makeCirclur(BufferedImage image) {

        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fill(new Ellipse2D.Float(0, 0, w, h));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }

    
    public static void makeCirclur(String src, String dest) throws Exception {
        if (!FileUtil.existFile(src)) {
            return;
        }

        File src_file = new File(src);
        BufferedImage image = ImageIO.read(src_file);

        BufferedImage output = ImageUtil.makeCirclur(image);
        ImageIO.write(output, "PNG", new File(dest));
    }

    
    public static void makeCirclur(String src, String dest, Integer w, Integer h) throws Exception {
        if (!FileUtil.existFile(src)) {
            return;
        }

        File src_file = new File(src);
        BufferedImage image = ImageIO.read(src_file);

        BufferedImage output = ImageUtil.makeCirclur(ImageUtil.resizeImage(image, w, h));
        ImageIO.write(output, "PNG", new File(dest));
    }

   
    public static Map<String, Object> getImageMetaInfo(String src) throws Exception {
        if (!FileUtil.existFile(src)) {
            return null;
        }

        File src_file = new File(src);
        BufferedImage image = ImageIO.read(src_file);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("width", image.getWidth());
        result.put("height", image.getHeight());

        return result;
    }

    
    public static BufferedImage blurImage(BufferedImage image) {
        float ninth = 1.0f / 9.0f;
        float[] blurKernel = {
                ninth, ninth, ninth,
                ninth, ninth, ninth,
                ninth, ninth, ninth
        };

        Map<Key, Object> map = new HashMap<Key, Object>();

        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RenderingHints hints = new RenderingHints(map);
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
        return op.filter(image, null);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int w, int h) {

        BufferedImage resizedImage = new BufferedImage(w, h, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();

        return resizedImage;
    }

    public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, int w, int h) {

        BufferedImage resizedImage = new BufferedImage(w, h, type);
        Graphics2D g = resizedImage.createGraphics();

        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public static BufferedImage crop(BufferedImage originalImage, int x, int y, int w, int h) {
        //System.out.println(originalImage.getHeight());
        if (y > originalImage.getHeight()) {
            y = 0;
            h = originalImage.getHeight();
        }
        if (x > originalImage.getWidth()) {
            x = 0;
            w = originalImage.getWidth();
        }
        if (h > (originalImage.getHeight() - y)) {
            h = (originalImage.getHeight() - y);
        }
        if (w > (originalImage.getWidth() - x)) {
            w = (originalImage.getWidth() - x);
        }

        BufferedImage croppedImage = originalImage.getSubimage(x, y, w, h);

        return croppedImage;
    }

    public static void crop(String src, String dest, int x, int y, int w, int h) {
        try {
            if (!FileUtil.existFile(src)) {
                return;
            }

            File src_file = new File(src);
            BufferedImage originalImage = ImageIO.read(src_file);

            BufferedImage resizedImage = ImageUtil.crop(originalImage, x, y, w, h);
            ImageIO.write(resizedImage, "PNG", new File(dest));

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            FileUtil.deleteFile(src);
        } catch (Exception ex) {
            ex.printStackTrace();
            FileUtil.deleteFile(dest);
        }

    }
}
