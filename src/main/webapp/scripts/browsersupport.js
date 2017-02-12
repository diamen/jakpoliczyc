/* Opera && Internet Explorer support */
Array.prototype.fill = Array.prototype.fill || function(val){
  for (var i = 0; i < this.length; i++) {
      this[i] = val;
  }
  return this;
};