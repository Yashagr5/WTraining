-- Consolidated data for AppService (includes Categories, Apps, Comments, Downloads, Notifications)

-- Categories data
INSERT INTO categories (name, description, app_count, is_featured) VALUES
('Games', 'Entertainment and gaming applications', 1, true),
('Social', 'Social networking and communication apps', 2, true),
('Productivity', 'Tools and utilities for productivity', 1, false),
('Health & Fitness', 'Health monitoring and fitness tracking apps', 1, true),
('Education', 'Learning and educational applications', 1, false),
('Entertainment', 'Media and entertainment applications', 1, true),
('Shopping', 'E-commerce and shopping applications', 1, false),
('Travel', 'Travel and navigation applications', 1, false),
('Photography', 'Photo editing and camera applications', 0, false),
('Music & Audio', 'Music streaming and audio applications', 1, true);

-- Apps data
INSERT INTO apps (name, description, version, owner_id, category_id, rating, download_count, size_mb, is_visible, created_at, updated_at) VALUES
('WhatsApp Messenger', 'Free messaging and video calling app', '2.24.1', 1, 2, 4.5, 5000000000, 45.2, true, '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
('Instagram', 'Photo and video sharing social network', '312.0', 1, 2, 4.3, 2000000000, 78.5, true, '2024-01-16 11:30:00', '2024-01-16 11:30:00'),
('Candy Crush Saga', 'Match-three puzzle video game', '1.268.0', 2, 1, 4.4, 1000000000, 125.8, true, '2024-01-17 09:15:00', '2024-01-17 09:15:00'),
('Microsoft Office', 'Productivity suite for documents and presentations', '16.0.17', 3, 3, 4.2, 500000000, 234.7, true, '2024-01-18 14:45:00', '2024-01-18 14:45:00'),
('MyFitnessPal', 'Calorie counter and diet tracker', '23.29.0', 3, 4, 4.6, 200000000, 89.3, true, '2024-01-19 16:20:00', '2024-01-19 16:20:00'),
('Duolingo', 'Language learning platform', '5.142.4', 4, 5, 4.7, 300000000, 67.4, true, '2024-01-20 08:30:00', '2024-01-20 08:30:00'),
('Netflix', 'Video streaming entertainment service', '8.109.0', 4, 6, 4.1, 1500000000, 156.9, true, '2024-01-21 12:10:00', '2024-01-21 12:10:00'),
('Amazon Shopping', 'Online shopping and e-commerce', '24.25.2', 5, 7, 4.0, 800000000, 112.6, true, '2024-01-22 15:40:00', '2024-01-22 15:40:00'),
('Google Maps', 'Navigation and local business discovery', '11.108.0', 5, 8, 4.8, 3000000000, 98.7, true, '2024-01-23 10:25:00', '2024-01-23 10:25:00'),
('Spotify Music', 'Music streaming and podcast platform', '8.8.96', 2, 10, 4.5, 1200000000, 143.2, true, '2024-01-24 13:55:00', '2024-01-24 13:55:00');

-- Comments data
INSERT INTO comments (application_id, user_id, description, rating, created_date, is_flagged, helpful_count) VALUES
(1, 1, 'Great messaging app! Very reliable and easy to use.', 5, '2024-01-25', false, 15),
(1, 2, 'Love the video calling feature. Crystal clear quality.', 4, '2024-01-25', false, 8),
(2, 3, 'Amazing photo filters and stories feature.', 5, '2024-01-26', false, 12),
(2, 4, 'Good app but uses too much battery sometimes.', 3, '2024-01-26', false, 3),
(3, 1, 'Addictive puzzle game! Perfect for passing time.', 4, '2024-01-27', false, 20),
(3, 5, 'Too many ads but gameplay is fun.', 3, '2024-01-27', false, 5),
(4, 2, 'Essential for work productivity. Great collaboration features.', 5, '2024-01-28', false, 18),
(4, 3, 'Interface could be more intuitive but very powerful.', 4, '2024-01-28', false, 7),
(5, 4, 'Excellent for tracking calories and workouts.', 5, '2024-01-29', false, 25),
(5, 5, 'Helped me lose 10 pounds! Highly recommend.', 5, '2024-01-29', false, 30);

-- Downloads data
INSERT INTO downloads (application_id, user_id, download_date, download_status, device_info, ip_address, download_size_mb, download_duration_seconds, is_successful, error_message) VALUES
(1, 1, '2024-01-25', 'COMPLETED', 'Samsung Galaxy S23', '192.168.1.100', 45.2, 120, true, null),
(1, 2, '2024-01-25', 'COMPLETED', 'iPhone 15 Pro', '192.168.1.101', 45.2, 95, true, null),
(2, 3, '2024-01-26', 'COMPLETED', 'OnePlus 11', '192.168.1.102', 78.5, 180, true, null),
(3, 1, '2024-01-27', 'COMPLETED', 'Samsung Galaxy S23', '192.168.1.100', 125.8, 240, true, null),
(4, 2, '2024-01-28', 'COMPLETED', 'iPhone 15 Pro', '192.168.1.101', 234.7, 420, true, null),
(5, 4, '2024-01-29', 'COMPLETED', 'Xiaomi Mi 13', '192.168.1.103', 89.3, 165, true, null);

-- Notifications data
INSERT INTO notifications (recipient_id, application_id, title, description, notification_date, notification_type, is_sent, priority, recipient_email, sent_date, retry_count) VALUES
(1, null, 'Welcome to PlayStore!', 'Thank you for joining our platform. Explore amazing apps!', '2024-01-15', 'USER', true, 'MEDIUM', 'john.doe@email.com', '2024-01-15', 0),
(2, 2, 'New App Available', 'Instagram has been updated with new features!', '2024-01-16', 'UPDATE', true, 'LOW', 'jane.smith@email.com', '2024-01-16', 0),
(1, 1, 'Download Complete', 'WhatsApp Messenger has been successfully downloaded.', '2024-01-25', 'DOWNLOAD', true, 'HIGH', 'john.doe@email.com', '2024-01-25', 0),
(1, null, 'App Downloaded', 'Your app WhatsApp Messenger was downloaded!', '2024-01-25', 'OWNER', true, 'MEDIUM', 'owner1@email.com', '2024-01-25', 0);
