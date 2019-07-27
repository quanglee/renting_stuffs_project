
module.exports = class Utils {
  static toBoolean(obj, property) {
    obj[property] = Boolean(obj[property]);
  }
};
