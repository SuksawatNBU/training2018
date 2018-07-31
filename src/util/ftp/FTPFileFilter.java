package util.ftp;

import org.apache.commons.net.ftp.FTPFile;

/**
 * Filter (a.txt, *.txt, a.*)
 * @author 150709Admin
 *
 */
public class FTPFileFilter implements org.apache.commons.net.ftp.FTPFileFilter {

	private String filter;
	
	public FTPFileFilter(String filter) {
		this.filter = filter;
	}
	
	@Override
	public boolean accept(FTPFile ftpFile) {
		
		if (filter == null) {
			return ftpFile.isFile();
		} else {
			int indexOfStar = filter.indexOf("*");
			if (indexOfStar == -1) {
				return (ftpFile.isFile() && ftpFile.getName().equals(filter));
				
			} else if (indexOfStar == 0) {
				return (ftpFile.isFile() && ftpFile.getName().endsWith(filter.substring(indexOfStar + 1)));		
				
			} else if (indexOfStar == filter.length() - 1) {
				return (ftpFile.isFile() && ftpFile.getName().startsWith(filter.substring(0, indexOfStar)));
			} else {
				if (ftpFile.isFile() == false) {
					return false;
				} else {
					String wildcardPrefix = filter.substring(0, indexOfStar);
					String wildcardPostfix = filter.substring(indexOfStar + 1, filter.length());
					String fileNamePrefix = ftpFile.getName().substring(0, indexOfStar);
					String fileNamePostfix = ftpFile.getName().substring(ftpFile.getName().length() - wildcardPostfix.length(), ftpFile.getName().length());
					
					return (wildcardPrefix.equals(fileNamePrefix) && wildcardPostfix.equals(fileNamePostfix));
				}
				
			}
			
		}
		
	}
}

