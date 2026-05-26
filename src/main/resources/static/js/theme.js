// Theme toggle - day (red+white) / night (dark)
(function() {
  var saved = localStorage.getItem('ttms-theme') || 'light';
  document.documentElement.setAttribute('data-theme', saved);

  window.toggleTheme = function() {
    var current = document.documentElement.getAttribute('data-theme');
    var next = current === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', next);
    localStorage.setItem('ttms-theme', next);
    updateIcon(next);
  };

  window.updateIcon = function(theme) {
    var btn = document.getElementById('theme-toggle-btn');
    if (btn) btn.textContent = theme === 'dark' ? '☀' : '☾';
  };
})();
