package kodlamaio.hrms.business.constans;

public class Messages {
	
	/* JobTitle */
	public static String jobTitleAdded = "İş pozisyonu eklendi";
	public static String checkIfTitleExists="Aynı isimde iş pozisyonu mevcut";

	/* Employer   */
	public static String employerAdded = "İş Veren eklendi.Kaydın tamamlanması için doğrulama kodu gönderildi ";
	public static String employerInsertionError="Doğrulama kodu ve onay işlemlerinde hata!";
	public static String checkIfDomainExists="Aynı domaine ait mail adresi kullanılmalı";
	
	/* Candidate  */ 
	public static String candidateAdded = "İş arayan eklendi.Kaydın tamamlanması için doğrulama kodu gönderildi ";
	public static String candidateInsertionError="Doğrulama kodu ve onay işlemlerinde hata!";
	public static String checkIfIdentityNumberExists="Aynı tc kimlik numarasına ait kayıt var";

	/* Validate */
	public static String validateMessage="Boş olan kısımlar var";
	public static String checkIfPasswordExists="Şifreler uyuşmuyor";
	public static String checkIfMailExists="Aynı mail adresi mevcut";
	public static String validateSuccessMernis="Mernis doğrulaması başarılı";
	public static String validateErrorMernis="Mernis doğrulaması başarısız";
}
