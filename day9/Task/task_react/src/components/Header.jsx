import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  const headerStyle = {
    backgroundColor: '#004080',
    color: 'white',
    padding: '15px 20px',
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center'
  };

  const linkStyle = {
    marginLeft: '15px',
    backgroundColor: 'white',
    color: '#004080',
    padding: '8px 12px',
    borderRadius: '4px',
    textDecoration: 'none',
    fontWeight: 'bold'
  };

  return (
    <header style={headerStyle}>
      <h1>Employee Management System</h1>
      <div>
        <Link to="/login" style={linkStyle}>Login</Link>
        <Link to="/register" style={{ ...linkStyle, backgroundColor: '#28a745', color: 'white' }}>Register</Link>
      </div>
    </header>
  );
};

export default Header;