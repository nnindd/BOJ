select BOOK_ID, AUTHOR_NAME, date_format(PUBLISHED_DATE,"%Y-%m-%d") as PUBLISHED_DATE
from BOOK as b, AUTHOR as a
where CATEGORY = "경제" and b.AUTHOR_ID = a.AUTHOR_ID
order by PUBLISHED_DATE;