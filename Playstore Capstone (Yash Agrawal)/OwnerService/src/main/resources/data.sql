-- Sample data for OwnerService with JWT-compatible fields
-- Password for all sample owners is: password123
INSERT INTO owners (owner_name, email, phone, password, created_at, is_active, approval_status, company_name, business_address, business_type) VALUES
('techcorp_admin', 'admin@techcorp.com', '9876543220', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-10 08:00:00', true, 'APPROVED', 'TechCorp Inc', '100 Tech Park, Silicon Valley', 'Software Development'),
('gamestudio_dev', 'contact@gamestudio.com', '9876543221', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-11 09:30:00', true, 'APPROVED', 'GameStudio Ltd', '200 Game Ave, Los Angeles', 'Gaming'),
('healthapp_team', 'info@healthapp.com', '9876543222', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-12 10:15:00', true, 'APPROVED', 'HealthApp Solutions', '300 Medical Center, Boston', 'Healthcare'),
('edutech_support', 'support@edutech.com', '9876543223', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-13 11:45:00', true, 'PENDING', 'EduTech Innovations', '400 Education Blvd, Austin', 'Education Technology'),
('social_admin', 'hello@socialmedia.com', '9876543224', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-14 13:20:00', true, 'APPROVED', 'Social Media Corp', '500 Social St, New York', 'Social Media');
