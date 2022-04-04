package tools.sapcx.commerce.toolkit.email.attachments;

import java.io.File;
import java.io.IOException;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.commons.mail.EmailException;

public class FileBasedHtmlEmailAttachmentBuilder extends AbstractHtmlEmailAttachmentBuilder {
	private File file;

	public FileBasedHtmlEmailAttachmentBuilder(File file) {
		this.file = file;
		this.name(file.getName());
	}

	@Override
	protected DataSource getDataSource() throws EmailException {
		String filePath = file.getAbsolutePath();
		try {
			if (!file.exists()) {
				throw new IOException("\"" + filePath + "\" does not exist");
			} else {
				return new FileDataSource(file);
			}
		} catch (IOException var4) {
			throw new EmailException("Cannot attach file \"" + filePath + "\"", var4);
		}
	}
}
