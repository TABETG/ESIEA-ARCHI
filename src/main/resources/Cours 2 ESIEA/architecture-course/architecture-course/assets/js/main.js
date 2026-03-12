
document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('[data-toggle]').forEach(btn => {
    btn.addEventListener('click', () => {
      const target = document.getElementById(btn.dataset.toggle);
      if (target) target.classList.toggle('hidden');
    });
  });

  const links = [...document.querySelectorAll('.toc a[href^="#"]')];
  const sections = links.map(a => document.querySelector(a.getAttribute('href'))).filter(Boolean);
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      const id = '#' + entry.target.id;
      const link = document.querySelector('.toc a[href="' + id + '"]');
      if (link && entry.isIntersecting) {
        document.querySelectorAll('.toc a').forEach(a => a.style.fontWeight = '500');
        link.style.fontWeight = '800';
      }
    });
  }, {rootMargin: '-20% 0px -65% 0px'});
  sections.forEach(s => observer.observe(s));
});
