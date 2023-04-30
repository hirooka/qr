package qr.api;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@Slf4j
@RequestMapping("api")
@RestController()
public class QrRestController {

  @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
  BufferedImage get(
      @RequestParam(required = false) String content,
      @RequestParam(required = false) Integer width,
      @RequestParam(required = false) Integer height
  ) {
    if (ObjectUtils.isEmpty(content)) {
      content = "https://dqw.hirooka.pro";
    }
    if (ObjectUtils.isEmpty(width) || width < 128) {
      width = 128;
    }
    if (ObjectUtils.isEmpty(height) || height < 128) {
      height = 128;
    }
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    try {
      BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
      return MatrixToImageWriter.toBufferedImage(bitMatrix);
    } catch (WriterException e) {
      throw new RuntimeException(e);
    }
  }
}
