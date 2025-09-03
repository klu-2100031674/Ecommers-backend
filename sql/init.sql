-- NewBuy E-Commerce Database Initialization
-- This file will be executed when the MySQL container starts for the first time

USE ecommerce;

-- Create an admin user for testing
-- Password: admin123 (hashed)
INSERT INTO users (user_name, email, Mobile_Number, password, emp_active, admin) 
VALUES ('Admin User', 'admin@newbuy.com', '9999999999', 'admin123', true, 'true')
ON DUPLICATE KEY UPDATE user_name = user_name;

-- Insert sample products
INSERT INTO products (name, type, color, price, stock_quantity, description) VALUES
('Classic Water Bottle', '1 Liter', 'Blue', 25.99, 100, 'High-quality stainless steel water bottle perfect for daily hydration needs.'),
('Premium Travel Mug', '500ml', 'Black', 35.99, 50, 'Insulated travel mug that keeps beverages hot for 8 hours and cold for 12 hours.'),
('Eco-Friendly Bottle', '750ml', 'Green', 29.99, 75, 'Made from recycled materials, perfect for environmentally conscious users.'),
('Sports Water Bottle', '1.5 Liter', 'Red', 32.99, 60, 'Large capacity bottle ideal for sports and outdoor activities.'),
('Kids Water Bottle', '350ml', 'Pink', 19.99, 80, 'Fun and colorful water bottle designed specifically for children.')
ON DUPLICATE KEY UPDATE name = name;

-- Create indexes for better performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_cart_user_id ON cart(user_id);

-- Display initialization success message
SELECT 'Database initialization completed successfully!' as message;