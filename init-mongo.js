db = db.getSiblingDB('DiscountService');
db.createUser({
  user: 'root',
  pwd: 'Ab123456',
  roles: [{ role: 'readWrite', db: 'DiscountService' }]
});