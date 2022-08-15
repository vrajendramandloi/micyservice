insert into myoutuber2020.USER_TABLE 
(	USER_REGISTER_DATE, NAME_PREFIX, FULL_NAME, EMAIL_ID, MOBILE_NUM, WEBSITE, 
	CHANNEL_NAME, YOUTUBE_URL, ADDRESS, CITY, COUNTRY, PINCODE, DOB, NICHE ) 
values 
(	NOW(), 'Mrs.', 'Ruchi Chaturvedi Mandloi', 'ruchicmandloi@yahoo.com', '9111314759', 'ruchiStyleCorner.com','Ruchi Style Corner', 
	'https://youtube.com/ruchiStyleCorner', 'LIG-184 Kotra Sultanabad Bhopal', 'Bhopal','India','462003','1991-01-28','HOME_DECOR' );

insert into myoutuber2020.USER_TABLE 
(	USER_REGISTER_DATE, NAME_PREFIX, FULL_NAME, EMAIL_ID, MOBILE_NUM, WEBSITE, 
	CHANNEL_NAME, YOUTUBE_URL, ADDRESS, CITY, COUNTRY, PINCODE, DOB, NICHE ) 
values 
(	NOW(), 'Mr.', 'Vrajendra Singh Mandloi', 'imvrajendra@yahoo.com', '9987062476', 'unityAndInterest.com','Unity And Interest', 
	'https://youtube.com/unityAndInterest', 'LIG-184 Kotra Sultanabad Bhopal', 'Bhopal','India','462003','1988-03-23','TECHNOLOGY' );
	
commit;


insert into myoutuber2020.PLAN_DETAILS 
(	VALIDITY_TEXT, VALIDITY_DAYS, PLAN_NAME, PLAN_AMOUNT,PLAN_DISCOUNT, PLAN_CURRENCY, PLAN_DESC, PLAN_STATUS,COUNTRY_ID  ) 
values 
(	'1 Month', 30, 'FREE30', 0,0, 'INR', 'FREE30: plan has all Prime features, this included All data Security on your Mobile Device, Safe Notes, Events Management, Vacation + Campaing management. Only subscription popup will open everytime you open the app.', 
	'INACTIVE', 'INDIA' );
insert into myoutuber2020.PLAN_DETAILS 
(	VALIDITY_TEXT, VALIDITY_DAYS, PLAN_NAME, PLAN_AMOUNT,PLAN_DISCOUNT, PLAN_CURRENCY, PLAN_DESC, PLAN_STATUS,COUNTRY_ID  ) 
values 
(	'1 Year', 365, 'PRIME', 601,501, 'INR', 'PRIME: This included All data Security on your Mobile Device, Safe Notes, Events Management, Vacation + Campaing management. We will notify about weekly feeds about your niche.', 
	'ACTIVE', 'INDIA' );
insert into myoutuber2020.PLAN_DETAILS 
(	VALIDITY_TEXT, VALIDITY_DAYS, PLAN_NAME, PLAN_AMOUNT,PLAN_DISCOUNT, PLAN_CURRENCY, PLAN_DESC, PLAN_STATUS,COUNTRY_ID  ) 
values 
(	'5 Years', 1825, 'LIFETIME PRIME', 2031,1825, 'INR', 'LIFETIME PRIME: plan has all Prime features, this included All data Security on your Mobile Device, Safe Notes, Events Management, Vacation + Campaing management. Only subscription popup will open everytime you open the app.', 
	'ACTIVE', 'INDIA' );
insert into myoutuber2020.PLAN_DETAILS 
(	VALIDITY_TEXT, VALIDITY_DAYS, PLAN_NAME, PLAN_AMOUNT,PLAN_DISCOUNT, PLAN_CURRENCY, PLAN_DESC, PLAN_STATUS,COUNTRY_ID  ) 
values 
(	'2 Month', 60, 'ALPHA', 5001,4001, 'INR', 'ALPHA: plan has all Prime features, this included All data Security on your Mobile Device, Safe Notes, Events Management, Vacation + Campaing management. Only subscription popup will open everytime you open the app.', 
	'ACTIVE', 'INDIA' );

insert into myoutuber2020.PLAN_DETAILS 
(	VALIDITY_TEXT, VALIDITY_DAYS, PLAN_NAME, PLAN_AMOUNT,PLAN_DISCOUNT, PLAN_CURRENCY, PLAN_DESC, PLAN_STATUS,COUNTRY_ID  ) 
values 
(	'1 Year', 365, 'PRIME', 29,25, 'USD', 'PRIME: This included All data Security on your Mobile Device, Safe Notes, Events Management, Vacation + Campaing management. We will notify about weekly feeds about your niche.', 
	'ACTIVE', 'INTERNATIONAL' );
insert into myoutuber2020.PLAN_DETAILS 
(	VALIDITY_TEXT, VALIDITY_DAYS, PLAN_NAME, PLAN_AMOUNT,PLAN_DISCOUNT, PLAN_CURRENCY, PLAN_DESC, PLAN_STATUS,COUNTRY_ID  ) 
values 
(	'5 Years', 1825, 'LIFETIME PRIME', 149,99, 'USD', 'LIFETIME PRIME: plan has all Prime features, this included All data Security on your Mobile Device, Safe Notes, Events Management, Vacation + Campaing management. Only subscription popup will open everytime you open the app.', 
	'ACTIVE', 'INTERNATIONAL' );


commit;
