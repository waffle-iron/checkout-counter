package com.mavaze.checkout.domain;

public enum Role {
    ADMIN, 				// Super user who can do anything
    INVENTORY_MANAGER, 	// Create and update products
    SALES_MANAGER, 		// Create categories and assign/unassign to/from products
    CASHIER, 			// Generate invoices
    CUSTOMER			// Get product list
}