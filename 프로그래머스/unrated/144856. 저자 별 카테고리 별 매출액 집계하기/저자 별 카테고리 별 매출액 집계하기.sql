select a.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(sales * price) as TOTAL_SALES
from book_sales s
join book b on s.book_id = b.book_id
join author a on b.author_id = a.author_id
where year(s.sales_date) = 2022 and month(s.sales_date) = 1
group by category, author_id
order by a.author_id, category desc;