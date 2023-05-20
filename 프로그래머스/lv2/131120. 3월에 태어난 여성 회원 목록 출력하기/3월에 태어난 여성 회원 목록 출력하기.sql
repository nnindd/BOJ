select MEMBER_ID, MEMBER_NAME, GENDER, concat(DATE_OF_BIRTH,"") as DATE_OF_BIRTH
from MEMBER_PROFILE
where GENDER = 'W' and DATE_OF_BIRTH like '%-03-%' and TLNO is not null
order by MEMBER_ID