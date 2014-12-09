/**
* <p>Title: Weixin.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2014-12-9
* @version 1.0
*/
package javaFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * <p>Title: Weixin.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2014-12-9
 * Email: vip6ming@126.com
 */
public class Weixin {

	public static void main(String[] args) {
		try {
			String inPath = "F:/workspace/Test_project/src/javaFile/FICContactsHeadImgFormatName.imageTable";
			String outPath = "F:/workspace/Test_project/src/javaFile/FICContactsHeadImgFormatName.imageTable";
			File inf = new File(inPath);
			File outf = new File(outPath);
			FileInputStream fis = new FileInputStream(inf);
			FileOutputStream fos = new FileOutputStream(outf);
			fos.write(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
