import React from 'react';
import { AccountView as AccountViewType } from '../types';

interface AccountViewProps {
  accountView: AccountViewType;
  setAccountView: (view: AccountViewType) => void;
  darkMode?: boolean;
}

export const AccountView: React.FC<AccountViewProps> = ({
  accountView,
  setAccountView,
  darkMode = false,
}) => {
  if (accountView === 'home') {
    return (
      <div className="flex-1 overflow-y-auto p-6">
        <div className="max-w-4xl mx-auto space-y-6">
          <div className="bg-gradient-to-br from-white to-[#f5f0e8] rounded-2xl border-2 border-[#8B7355] p-8 shadow-lg">
            <h1 className="text-2xl font-bold text-gray-900 mb-6">💰 안녕하세요, Aiion님</h1>
            <div className="space-y-4 text-gray-700 leading-relaxed">
              <p>가계부 관리 화면입니다.</p>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="flex-1 overflow-y-auto p-6">
      <div className="max-w-4xl mx-auto">
        <p className="text-gray-600">Account view: {accountView}</p>
      </div>
    </div>
  );
};

