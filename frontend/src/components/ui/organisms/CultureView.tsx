import React from 'react';
import { CultureView as CultureViewType } from '../types';

interface CultureViewProps {
  cultureView: CultureViewType;
  setCultureView: (view: CultureViewType) => void;
  darkMode?: boolean;
}

export const CultureView: React.FC<CultureViewProps> = ({
  cultureView,
  setCultureView,
  darkMode = false,
}) => {
  return (
    <div className={`flex-1 flex flex-col overflow-hidden ${darkMode ? 'bg-gray-900' : 'bg-[#e8e2d5]'}`}>
      <div className="flex-1 overflow-y-auto p-6">
        <div className="max-w-4xl mx-auto space-y-6">
          <h1 className="text-3xl font-bold text-gray-900">Culture</h1>
          <p className="text-gray-600">Culture view: {cultureView}</p>
        </div>
      </div>
    </div>
  );
};

