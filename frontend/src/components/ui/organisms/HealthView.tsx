import React from 'react';
import { HealthView as HealthViewType } from '../types';

interface HealthViewProps {
  healthView: HealthViewType;
  setHealthView: (view: HealthViewType) => void;
  darkMode?: boolean;
}

export const HealthView: React.FC<HealthViewProps> = ({
  healthView,
  setHealthView,
  darkMode = false,
}) => {
  return (
    <div className={`flex-1 flex flex-col overflow-hidden ${darkMode ? 'bg-gray-900' : 'bg-[#e8e2d5]'}`}>
      <div className="flex-1 overflow-y-auto p-6">
        <div className="max-w-4xl mx-auto space-y-6">
          <h1 className="text-3xl font-bold text-gray-900 text-center">Health Care</h1>
          <p className="text-gray-600">Health view: {healthView}</p>
        </div>
      </div>
    </div>
  );
};

