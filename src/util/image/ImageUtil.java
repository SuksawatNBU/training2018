package util.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

//import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.codec.binary.Base64;

import util.log.LogUtil;

public class ImageUtil {
	private final static int RATIO = 50;

	public static BufferedImage resizeImage(String originalImage, int width, int height) throws Exception {

		BufferedImage resizedImage = null;

		try {

			BufferedImage originalBufferedImage = ImageIO.read(new File(originalImage));
			int type = originalBufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalBufferedImage.getType();

			resizedImage = new BufferedImage(width, height, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalBufferedImage, 0, 0, width, height, null);
			g.dispose();

		} catch (Exception e) {
			throw e;
		}

		return resizedImage;
	}

	public static BufferedImage resizeImageWithHint(String originalImage, int width, int height) throws Exception {

		BufferedImage resizedImage = null;

		try {

			BufferedImage originalBufferedImage = ImageIO.read(new File(originalImage));
			int type = originalBufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalBufferedImage.getType();

			resizedImage = new BufferedImage(width, height, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalBufferedImage, 0, 0, width, height, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		} catch (Exception e) {
			throw e;
		}

		return resizedImage;
	}

	public static BufferedImage resizeImageWithHint(String originalImage) throws Exception {

		BufferedImage resizedImage = null;
		int percentWidth;
		int percentHeight;
		double ratio;

		try {
			BufferedImage originalBufferedImage = ImageIO.read(new File(originalImage));
			int imgWidth = originalBufferedImage.getWidth();
			int imgHeight = originalBufferedImage.getHeight();
			int type = originalBufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalBufferedImage.getType();

			ratio = (double) imgWidth / imgHeight;
			if (imgWidth > imgHeight) {
				percentWidth = RATIO;
				percentHeight = (int) (percentWidth / ratio);
			} else {
				percentHeight = RATIO;
				percentWidth = (int) (percentHeight * ratio);
			}

			resizedImage = new BufferedImage(percentWidth, percentHeight, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalBufferedImage.getScaledInstance(percentWidth, percentHeight, Image.SCALE_SMOOTH), 0, 0, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		} catch (Exception e) {
			throw e;
		}

		return resizedImage;
	}

	public static String getBase64(byte[] byteImage) throws Exception {
		String base64 = null;
		try {
			base64 = Base64.encodeBase64String(byteImage);
		} catch (Exception e) {
			throw e;
		}
		return base64;
	}

	public static String getBase64(BufferedImage bufferedImage) throws Exception {
		String base64 = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
			base64 = Base64.encodeBase64String(byteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			throw e;
		} finally {
			if (byteArrayOutputStream != null) {
				byteArrayOutputStream.close();
			}
		}
		return base64;
	}

	public static void main(String[] args) throws Exception {
		String inputImagePath = "D:/nanthaporn.p/2015/AOT/02-workspace/wk-dev/apps/app/images/default/no_image.jpg";
		String outputImagePath = "D:/nanthaporn.p/2015/AOT/02-workspace/wk-dev/apps/app/images/default/no_image_resize.jpg";

		ImageUtil.resizeImage(inputImagePath, 240, 270, outputImagePath, "");
	}

	/**
	 *
	 * @param image The image to be scaled
	 * @param newWidth The required width
	 * @param newHeight The required width
	 *
	 * @return outputImagePath
	 */
	public static String resizeImage(String inputImagePath, int newWidth, int newHeight, String outputImagePath, String contentType) throws Exception {

		LogUtil.UTIL.debug("resizeImage");
		try {
			Path path = Paths.get(inputImagePath);
//		    String contentType = Files.probeContentType(path);
		    LogUtil.UTIL.debug("contentType : " + contentType);
		    if (contentType.startsWith("image")) {
		    	BufferedImage imageIn = ImageIO.read(new File(inputImagePath));

				String[] arr = inputImagePath.split("\\.");
				String type = arr[arr.length - 1];

				// Make sure the aspect ratio is maintained, so the image is not distorted
				double thumbRatio = (double) newWidth / (double) newHeight;
				int imageWidth = imageIn.getWidth(null);
				int imageHeight = imageIn.getHeight(null);
				double aspectRatio = (double) imageWidth / (double) imageHeight;

				if (newWidth < imageWidth || newHeight < imageHeight) {
					if (thumbRatio < aspectRatio) {
						newHeight = (int) (newWidth / aspectRatio);
					} else {
						newWidth = (int) (newHeight * aspectRatio);
					}
				} else {
					newWidth = imageWidth;
					newHeight = imageHeight;
				}
				

				// Check if the image has transparent pixels
				boolean hasAlpha = ((BufferedImage) imageIn).getColorModel().hasAlpha();

				BufferedImage newImage;
				// Check if transparent pixels are available and set the color mode accordingly
				if (hasAlpha) {
					newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
				} else {
					newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				}

				// Draw the scaled image
				Graphics2D g = newImage.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(imageIn, 0, 0, newWidth, newHeight, null);

				ImageIO.write(newImage, type, new File(outputImagePath));
		    }
		} catch (Exception e) {
			LogUtil.UTIL.error(e);
			throw e;
		}

		return outputImagePath;
	}
}
