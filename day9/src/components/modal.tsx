import React, { useEffect, useRef } from "react";

import type { ModalProps } from "../types/modal";

const Modal: React.FC<ModalProps> = ({ isOpen, onClose, title, children }) => {
    const modalRef = useRef<HTMLDivElement>(null);

    if (!isOpen) return null;

    return (
        <div
            className="fixed inset-0 backdrop-blur-sm flex items-center justify-center z-50 transition-opacity duration-300"
            onClick={onClose}
        >
            <div
                ref={modalRef}
                className="bg-white rounded-lg shadow-2xl p-6 max-w-lg w-full mx-4 max-h-[80vh] overflow-y-auto transform transition-all duration-300 scale-95"
                onClick={(e) => e.stopPropagation()}
                role="dialog"
                aria-labelledby="modal-title"
                aria-modal="true"
                tabIndex={-1}
            >
                <h2
                    id="modal-title"
                    className="text-2xl font-semibold text-gray-800 mb-4"
                >
                    {title}
                </h2>
                {children}
            </div>
        </div>
    );
};

export default Modal;
