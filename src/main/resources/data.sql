-- test-data.sql
INSERT INTO article (id, title, content, hashtag, created_at, created_by, modified_at, modified_by)
VALUES (1, '테스트 게시글', '테스트 내용', '#테스트', NOW(), 'testuser', NOW(), 'testuser');

INSERT INTO article_comment (id, article_id, content, created_at, created_by, modified_at, modified_by)
VALUES (1, 1, '테스트 댓글', NOW(), 'testuser', NOW(), 'testuser');
