package com.imap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Кастомный эксепшен, выбрасывается в случае отсутсвия запрашиваемого эллемента.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item not found.")
public class NoSuchItemException extends Exception {

}
