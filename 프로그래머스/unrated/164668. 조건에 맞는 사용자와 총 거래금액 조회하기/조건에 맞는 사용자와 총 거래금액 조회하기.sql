select user_id, nickname, sum(price) as total_sales
from used_goods_user u
join used_goods_board b
on u.user_id = b.writer_id
where status = 'DONE'
group by user_id
having sum(price) >= 700000
order by sum(price);