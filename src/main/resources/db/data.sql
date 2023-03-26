INSERT INTO user_tb(user_name, user_password, user_email, user_profile, role, created_at) values('ssar', '1234', 'ssar@nate.com', '/images/dora.png', 'user', now());
INSERT INTO user_tb(user_name, user_password, user_email, user_profile, role, created_at) values('bori', '1234', 'bori@nate.com', '/images/dora.png', 'user', now());
INSERT INTO user_tb(user_name, user_password, user_email, user_profile, role, created_at) values('juho', '1234', 'joho@gmail.com', '/images/dora.png', 'user', now());

INSERT INTO company_tb(company_name, company_password, company_email, company_profile, role, created_at) values('samsung', '1234', 'samsung@gmail.com', '/images/samsung.png', 'company', now());
INSERT INTO company_tb(company_name, company_password, company_email, company_profile, role, created_at) values('lg', '1234', 'lg@gmail.com', '/images/lg.png', 'company', now());
INSERT INTO company_tb(company_name, company_password, company_email, company_profile, role, created_at) values('green', '1234', 'green@gmail.com', '/images/samsung.png','company', now());

INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('1', '삼성', '자바 기술자 모집합니다.', '정규직', '부산', '경력 2년이상', '초대졸이상', '5000', '자바 기술자를 뽑지만 스프링도 할줄 알아야 하고, 리액트도 할줄 아셔야 합니다.', now());
INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('1', '삼성', '스프링 기술자 모집합니다.', '정규직',  '서울', '경력 5년이상', '대졸이상', '1000', '스프링 기술자를 뽑지만 리액트도 할줄 알아야 하고, 플러터도 할줄 아셔야 합니다.', now());
INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('2', '엘지', '플러터 기술자 모집합니다.', '계약직', '부산', '신입' , '고졸이상', '3000', '플러터 기술자를 뽑지만 스프링도 할줄 알아야 하고, 자바도 할줄 아셔야 합니다.',  now());
INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('2', '엘지', '자바 기술자 모집합니다.', '계약직', '부산', '신입' , '고졸이상', '4000', '플러터 기술자를 뽑지만 스프링도 할줄 알아야 하고, 자바도 할줄 아셔야 합니다.',  now());
INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('3', '그린', '플러터 기술자 모집합니다.', '정규직', '서울', '경력 1년 이상' , '대졸이상', '3000', '플러터 기술자를 뽑지만 스프링도 할줄 알아야 하고, 자바도 할줄 아셔야 합니다.',  now());
INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('3', '그린', '자바 기술자 모집합니다.', '계약직', '부산', '신입' , '고졸이상', '5000', '플러터 기술자를 뽑지만 스프링도 할줄 알아야 하고, 자바도 할줄 아셔야 합니다.',  now());
INSERT INTO notice_tb(company_id, notice_companyname, notice_title, notice_employtype, notice_location, notice_career, notice_grade, notice_pay, notice_content, created_at) values('2', '엘지', '스프링 기술자 모집합니다.', '정규직', '서울', '경력 2년이상' , '대졸이상', '4000', '플러터 기술자를 뽑지만 스프링도 할줄 알아야 하고, 자바도 할줄 아셔야 합니다.',  now());

INSERT INTO apply_tb(resume_id, notice_id, user_id, company_id, created_at) values('1', '1', '1', '1', now());
INSERT INTO apply_tb(resume_id, notice_id, user_id, company_id, created_at) values('2', '2', '2', '1', now());
INSERT INTO apply_tb(resume_id, notice_id, user_id, company_id, created_at) values('3', '3', '3', '2', now());
INSERT INTO apply_tb(resume_id, notice_id, user_id, company_id, created_at) values('2', '5', '2', '2', now());
INSERT INTO apply_tb(resume_id, notice_id, user_id, company_id, created_at) values('3', '4', '3', '3', now());

INSERT INTO resume_tb(user_id, resume_username, resume_birth, resume_email, resume_address, resume_location, resume_career, resume_grade, resume_phone_number, resume_sex, resume_content, created_at) values('1', '안정훈', '1997-03-15', 'an@nate.com', '부산광역시 북구 덕천동', '부산', '경력 2년', '학사', '01011112222', '남', '나', now());
INSERT INTO resume_tb(user_id, resume_username, resume_birth, resume_email, resume_address, resume_location, resume_career, resume_grade, resume_phone_number, resume_sex, resume_content, created_at) values('2', '김정욱', '1994-09-15', 'kj@nate.com', '부산광역시 해운대구', '부산', '경력 3년', '석사', '0101841532', '남', '너', now());
INSERT INTO resume_tb(user_id, resume_username, resume_birth, resume_email, resume_address, resume_location, resume_career, resume_grade, resume_phone_number, resume_sex, resume_content, created_at) values('3', '이현석', '1970-11-28', 'good@nate.com', '서울특별시 강남구', '서울', '신입', '학사', '01065413344', '남', '뭐', now());

INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('1', '1', 'Java', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('1', '1', 'JavaScript', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('1', '1', 'React', now());

INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('2', '2', 'JavaScript', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('2', '2', 'Flutter', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('2', '2', 'React', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('2', '2', 'Jsp', now());

INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('1', '2', 'Java', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('1', '2', 'JavaScript', now());
INSERT INTO userskill_tb(resume_id, user_id, userskill_name, created_at) values('1', '2', 'React', now());

INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('1', '1', 'React', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('1', '1', 'Spirng', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('1', '1', 'Java', now());

INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('2', '1', 'JavaScript', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('2', '1', 'Spirng', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('2', '1', 'Flutter', now());

INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('3', '2', 'React', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('3', '2', 'Spirng', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('3', '2', 'Java', now());

INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('4', '2', 'JavaScript', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('4', '2', 'Spirng', now());
INSERT INTO companyskill_tb(notice_id, company_id, companyskill_name, created_at) values('4', '2', 'Flutter', now());

commit;

