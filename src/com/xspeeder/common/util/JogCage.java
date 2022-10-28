/**
 * Copyright University Development Team
 * All rights reserved.
 * 
 */

package com.xspeeder.common.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.github.cage.Cage;
import com.github.cage.GCage;


public class JogCage {

    private String captchaDirectory = "";

    private String fileName = "cage_";

    private String token = "";

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *        the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *        the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the captchaDirectory
     */
    public String getCaptchaDirectory() {
        return captchaDirectory;
    }

    /**
     * @param captchaDirectory
     *        the captchaDirectory to set
     */
    public void setCaptchaDirectory(String captchaDirectory) {
        this.captchaDirectory = captchaDirectory;
    }

   
    public boolean generate( String dir, String namePrefix,
            String namePostfix, String text) throws IOException {

        Cage cage = new GCage();
        Date date = new Date();
        if (namePrefix == null) {
            long currentTime = date.getTime();
            this.setFileName("cage_" + currentTime + namePostfix);
        }

        this.setCaptchaDirectory(dir);

        final OutputStream os = new FileOutputStream(this.getCaptchaDirectory() + this.getFileName(), false);
        this.setToken(text != null ? text : cage.getTokenGenerator().next());
        try {
            cage.draw(this.getToken(), os);
            // net.sourceforge.jtds.util.Logger.println("Created Captcha Image"
            // + this.getCaptchaDirectory() + this.getFileName() +
            // this.getFileExtension() );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            os.close();
        }

        return true;
    }
    
   
    public byte[] generate() {
        
        ByteArrayOutputStream captcha_stream = new ByteArrayOutputStream();
        
        Cage cage = new GCage();
        this.setToken(cage.getTokenGenerator().next());
        
        try {
            cage.draw(this.getToken(), captcha_stream );
            // net.sourceforge.jtds.util.Logger.println("Created Captcha Image"
            // + this.getCaptchaDirectory() + this.getFileName() +
            // this.getFileExtension() );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return captcha_stream.toByteArray();
    }
    
   
    public String generateTokenOnly() 
    {
    	Cage cage = new GCage();
    	return cage.getTokenGenerator().next();
    }
}
