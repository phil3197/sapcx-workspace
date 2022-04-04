package tools.sapcx.commerce.toolkit.email.attachments;

import javax.activation.DataSource;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public abstract class AbstractHtmlEmailAttachmentBuilder implements HtmlEmailAttachmentBuilder {
	private String name;
	private String description;

	@Override
	public HtmlEmailAttachmentBuilder name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public HtmlEmailAttachmentBuilder description(String description) {
		this.description = description;
		return this;
	}

	@Override
	public void attach(HtmlEmail email) throws EmailException {
		email.attach(getDataSource(), name, description);
	}

	protected abstract DataSource getDataSource() throws EmailException;
}
