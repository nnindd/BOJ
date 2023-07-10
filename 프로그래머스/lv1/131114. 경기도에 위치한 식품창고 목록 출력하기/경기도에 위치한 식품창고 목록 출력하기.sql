select WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, if(FREEZER_YN is null, 'N', FREEZER_YN) as FREEZER_YN
from food_warehouse
where address like '경기도%'
order by warehouse_id;