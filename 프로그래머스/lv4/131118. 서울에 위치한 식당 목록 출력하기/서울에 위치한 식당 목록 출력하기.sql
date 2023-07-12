select i.rest_id, rest_name, food_type, favorites, address, score
from rest_info i
join (
    select rest_id, round(avg(review_score), 2) as score
    from rest_review
    group by rest_id
) r
on i.rest_id = r.rest_id
where address like '서울%'
order by score desc, favorites desc;
    



