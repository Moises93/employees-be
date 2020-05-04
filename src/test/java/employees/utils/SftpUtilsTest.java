package employees.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


public class SftpUtilsTest {

	@Test
	public void test() throws SftpException, JSchException {
		SftpUtils sftp = new SftpUtils();
		//sftp.putFile("src/main/resources/file.csv");
		
	}

}
