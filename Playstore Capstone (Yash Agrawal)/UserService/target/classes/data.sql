-- Sample data for UserService with JWT-compatible fields
-- Password for all sample users is: password123
INSERT INTO users (username, email, phone, password, created_at, is_active, full_name, address) VALUES
('john_doe', 'john.doe@email.com', '9876543210', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-15 10:30:00', true, 'John Doe', '123 Main St, City'),
('jane_smith', 'jane.smith@email.com', '9876543211', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-16 11:15:00', true, 'Jane Smith', '456 Oak Ave, City'),
('mike_wilson', 'mike.wilson@email.com', '9876543212', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-17 09:45:00', true, 'Mike Wilson', '789 Pine Rd, City'),
('sarah_johnson', 'sarah.johnson@email.com', '9876543213', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-18 14:20:00', true, 'Sarah Johnson', '321 Elm St, City'),
('alex_brown', 'alex.brown@email.com', '9876543214', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', '2024-01-19 16:10:00', true, 'Alex Brown', '654 Maple Dr, City');
