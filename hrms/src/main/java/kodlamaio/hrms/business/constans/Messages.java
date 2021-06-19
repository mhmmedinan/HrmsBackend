package kodlamaio.hrms.business.constans;

public class Messages {

	/* JobTitle */
	public static String jobTitleAdded = "İş pozisyonu eklendi";
	public static String checkIfTitleExists = "Aynı isimde iş pozisyonu mevcut";
	public static String listAllTitle = "Tüm iş pozisyonu bilgileri listelendi";

	/* Employer */
	public static String employerAdded = "İş Veren eklendi.Kaydın tamamlanması için doğrulama kodu gönderildi ";
	public static String employerInsertionError = "Doğrulama kodu ve onay işlemlerinde hata!";
	public static String checkIfDomainExists = "Aynı domaine ait mail adresi kullanılmalı";
	public static String listEmployerAll = "Tüm iş veren bilgileri listelendi";

	/* Candidate */
	public static String candidateAdded = "İş arayan eklendi.Kaydın tamamlanması için doğrulama kodu gönderildi ";
	public static String candidateDeleted = "İş arayan silindi. ";
	public static String candidateInsertionError = "Doğrulama kodu ve onay işlemlerinde hata!";
	public static String checkIfIdentityNumberExists = "Aynı tc kimlik numarasına ait kayıt var";
	public static String listAll = "Tüm iş arayan bilgileri listelendi";

	/* City */

	public static String checkIfCityNameExists = "Aynı şehire ait kayıt var";
	public static String cityAdded = "Şehir eklendi";
	public static String listCityAll = "Tüm şehir bilgileri listelendi";

	
	/* WayOfWorking */

	public static String workingAdded = "Çalışma şekli eklendi";
	public static String workingListed = "Tüm çalışma şekilleri listelendi";
	
	
	/* EducationLevel */

	public static String levelAdded = "Eğitim seviyesi eklendi";
	public static String levelListed = "Tüm eğitim seviyeleri listelendi";

	/* JobAdvert */

	public static String jobAdvertAdded = "İş ilanı eklendi";
	public static String jobAdvertUpdated = "İş ilanı güncellendi";
	public static String noSuchJobAdvert = "Böyle bir iş ilanı yok";
	public static String closeJobAdvert = "İş ilanı kapalı";
	public static String openJobAdvert = "İş ilanı açık";
	public static String successCloseJobAdvert = "İş ilanı başarıyla kapatılmıştır";
	public static String successOpenJobAdvert = "İş ilanı başarıyla açılmıştır";
	public static String listEmployerTrueAll = "İş veren bilgisine göre tüm aktif iş ilanı listelendi";
	public static String listTrueJobAdvertAll = "Tüm aktif iş ilanı bilgileri listelendi";
	public static String listLastApply = "Son eklenme tarihine göre iş ilanları  listelendi";
	public static String jobAdvertDeleted="İş ilanı silindi";

	/* School */
	public static String schoolAdded = "Okul bilgisi eklendi";
	public static String listCandidateSchool = "İş arayan bilgisine göre okul listelendi";
	public static String listSchool = "Tüm okul bilgileri listelendi";

	/* JobExperience */

	public static String jobExperienceAdded = "İş tecrübe bilgisi eklendi";
	public static String listCandidate = "İş arayan bilgisine göre listelendi";
	public static String listAllExperience = "İş tecrübe bilgileri eklendi";

	/* Language */

	public static String languageAdded = "Yabancı dil eklendi";
	public static String listAllLang = "Tüm yabancı dil bilgileri listelendi";

	/* Technologie */

	public static String techAdded = "Teknoloji bilgisi eklendi";
	public static String listtech = "Tüm teknoloji bilgileri  listelendi";

	/* Resume */

	public static String resumeAdded = "Özgeçmiş eklendi";
	public static String resumeDeleted = "Özgeçmiş silindi";
	public static String listCandidateCv = "İş arayan bilgisine göre cv listelendi";
	public static String listAllCv = "Tüm cv listelendi";

	/* Global */
	public static String checkIfPasswordExists = "Şifreler uyuşmuyor";
	public static String checkIfMailExists = "Aynı mail adresi mevcut";

	/* Mernis */
	public static String validateSuccessMernis = "Mernis doğrulaması başarılı";
	public static String validateErrorMernis = "Mernis doğrulaması başarısız";

	/* User */
	public static String listUser = "Tüm kullanıcılar listelendi";
}
