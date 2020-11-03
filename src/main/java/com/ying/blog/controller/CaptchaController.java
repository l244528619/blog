/**
 * Chsi
 * Created on 2020年10月29日
 */
package com.ying.blog.controller;

import static com.ying.blog.common.Constants.SESSION_CAPTCHA_NAME;

import com.ying.blog.common.YingCaptcha;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lil <a href="mailto:lil@chsi.com.cn">lil</a>
 * @version $
 */
@Controller
@RequestMapping("captcha")
public class CaptchaController extends BaseController {

    @RequestMapping("getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        int width = 81;
        int height = 20;

        try {
            String sWidth = request.getParameter("width");
            String sHeight = request.getParameter("height");
            if (!(null == sWidth || null == sHeight)) {
                width = Integer.parseInt(sWidth);
                height = Integer.parseInt(sHeight);
            }
        } catch (Exception Ex) {
        }

        // 在内存中创建图象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形句柄
        Graphics g = null;

        try {
            g = image.getGraphics();

            // 设置背景色
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);

            // 画边框
            g.setColor(Color.black);
            g.drawRect(0, 0, width - 1, height - 1);

            // 使用Java随机类
            Random random = new Random();
            String ID = String.valueOf(random.nextInt(10000));

            // ID.length()必然>=1且<=4

            switch (ID.length()) {
                case 1:
                    ID = "000" + ID;
                    break;
                case 2:
                    ID = "00" + ID;
                    break;
                case 3:
                    ID = "0" + ID;
                    break;
                case 4:
                    ID = ID.substring(0, 4);
                    break;
                default:
                    ID = "8888";
            }

            YingCaptcha.captchaSet.add(ID);

            // 将认证码显示到图象中
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(ID.substring(0, 1), 10, 17);

            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(ID.substring(1, 2), 25, 15);

            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(ID.substring(2, 3), 40, 17);

            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(ID.substring(3, 4), 55, 15);

            // 随机产生若干干扰点，使图象中的认证码不易被其它程序探测到

            for (int i = 0; i < 30; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                g.drawLine(x, y, x, y);
            }
        } catch (Exception e) {
            ;
        } finally {
            // 图象生效
            g.dispose();
        }

        // 输出图象到页面
        ServletOutputStream sops = null;

        try {
            sops = response.getOutputStream();
            ImageIO.write(image, "JPEG", sops);
        } catch (Exception e) {
            ;
        } finally {
            try {
                sops.close();
            } catch (Exception e) {

            }
        }
    }
}
