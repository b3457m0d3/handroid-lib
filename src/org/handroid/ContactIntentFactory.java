package org.handroid;

import android.content.Intent;
import android.net.Uri;

public class ContactIntentFactory {

	public static final Intent createEmailIntent(String[] recipients,
			String subject, String content) {
		Intent email = new Intent(android.content.Intent.ACTION_SEND);
		email.setType("plain/text").putExtra(Intent.EXTRA_EMAIL, recipients)
				.putExtra(Intent.EXTRA_SUBJECT, subject)
				.putExtra(Intent.EXTRA_TEXT, content);
		return email;
	}

	public static final Intent createDialIntent(String number) {
		Uri phoneUri = Uri.parse("tel:" + number);
		return new Intent(Intent.ACTION_DIAL, phoneUri);
	}

}
