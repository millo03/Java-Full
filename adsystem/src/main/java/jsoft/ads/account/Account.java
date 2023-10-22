package jsoft.ads.account;

import jsoft.objects.AccountObject;

public interface Account {
	public boolean addAccount(AccountObject item);
	public boolean editAccount(AccountObject item);
	public boolean delAccount(AccountObject item);

	// Lay id theo account_id or account_customer_id
	public Object getAccount(int id);
	// Lấy ra các accounts có chung account_type
	public Object getAccounts(byte similar, int at, byte total);
}
