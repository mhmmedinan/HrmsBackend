package kodlamaio.hrms.business.constans;

public class Messages {

	/* JobTitle */
	public static String jobTitleAdded = "İş pozisyonu eklendi";
	public static String checkIfTitleExists = "Aynı isimde iş pozisyonu mevcut";

	/* Employer */
	public static String employerAdded = "İş Veren eklendi.Kaydın tamamlanması için doğrulama kodu gönderildi ";
	public static String employerInsertionError = "Doğrulama kodu ve onay işlemlerinde hata!";
	public static String checkIfDomainExists = "Aynı domaine ait mail adresi kullanılmalı";

	/* Candidate */
	public static String candidateAdded = "İş arayan eklendi.Kaydın tamamlanması için doğrulama kodu gönderildi ";
	public static String candidateInsertionError = "Doğrulama kodu ve onay işlemlerinde hata!";
	public static String checkIfIdentityNumberExists = "Aynı tc kimlik numarasına ait kayıt var";

	/* City */

	public static String checkIfCityNameExists = "Aynı şehire ait kayıt var";
	public static String cityAdded = "Şehir eklendi";

	/* JobAdvert */

	public static String jobAdvertAdded = "İş ilanı eklendi";
	public static String jobAdvertUpdated = "İş ilanı güncellendi";
	public static String noSuchJobAdvert = "Böyle bir iş ilanı yok";
	public static String closeJobAdvert = "İş ilanı kapalı";
	public static String openJobAdvert = "İş ilanı açık";
	public static String successCloseJobAdvert = "İş ilanı başarıyla kapatılmıştır";
	public static String successOpenJobAdvert = "İş ilanı başarıyla açılmıştır";

	/* Validate */
	public static String validateMessage = "Boş olan kısımlar var";
	public static String checkIfPasswordExists = "Şifreler uyuşmuyor";
	public static String checkIfMailExists = "Aynı mail adresi mevcut";
	public static String validateSuccessMernis = "Mernis doğrulaması başarılı";
	public static String validateErrorMernis = "Mernis doğrulaması başarısız";

}
