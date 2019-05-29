select distinct lab_id from t_labtag where tag_id = 1 or tag_id = 2
group by lab_id
having count(lab_id) = 2;

select count(distinct lab_id) from t_labtag where lab_id in(
  select distinct lab_id from t_labtag where tag_id = 1 or tag_id = 2 or tag_id = 3
  group by lab_id
  having count(lab_id) = 2
)